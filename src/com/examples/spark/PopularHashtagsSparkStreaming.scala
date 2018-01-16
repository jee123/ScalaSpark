package com.examples.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.streaming._
import org.apache.spark.streaming.twitter._
import org.apache.spark.streaming.StreamingContext._

/** Listens to a stream of Tweets and keeps track of the most popular
 *  hashtags over a 5 minute window.
 *  Followup tasks would include:
 *  		- keeping track of most popular word tweeted in general (refer not only to the hashtag as did in first case but everything in general).
 *  		- keeping track of average length of tweet that comes through or report length of the most popular tweet.
 *     
 */
object PopularHashtagsSparkStreaming {
  
    /** Makes sure only ERROR messages get logged to avoid log spam. */
  def setupLogging() = {
    import org.apache.log4j.{Level, Logger}   
    val rootLogger = Logger.getRootLogger()
    rootLogger.setLevel(Level.ERROR)   
  }
  
  /** Configures Twitter service credentials using twiter.txt in the main workspace directory */
  def setupTwitter() = {
    import scala.io.Source
    
    for (line <- Source.fromFile("../twitter.txt").getLines) {
      val fields = line.split(" ")
      if (fields.length == 2) {
        System.setProperty("twitter4j.oauth." + fields(0), fields(1))
      }
    }
  }
  
  /** Our main function where the action happens */
  def main(args: Array[String]) {

    // Configure Twitter credentials using twitter.txt
    setupTwitter()
    
    // Set up a Spark streaming context named "PopularHashtags" that runs locally using
    // all CPU cores and one-second batches of data
    val ssc = new StreamingContext("local[*]", "PopularHashtags", Seconds(1))
    
    // Get rid of log spam (should be called after the context is set up)
    setupLogging()

    // Create a DStream from Twitter using our streaming context
    val tweets = TwitterUtils.createStream(ssc, None)
    
    // Now extract the text of each status update into DStreams using map()
    val statuses = tweets.map(status => status.getText())
    
    // Blow out each word into a new DStream
    //val tweetwords = statuses.flatMap(tweetText => tweetText.split(" "))
    val tweetwords = statuses.flatMap(tweetText => tweetText.split(" \\s+"))
    //val tweetwords = statuses.flatMap(tweetText => tweetText.split(" \\s+")).filter(word => !word.contains("RT") && !word.contains("@"))
    
    
    // Now eliminate anything that's not a hashtag
    //val hashtags = tweetwords.filter(word => word.startsWith("#"))
    val hashtags = tweetwords.map(_.replaceAll("[,.!?:;]", "").trim.toLowerCase)
                             .filter(x => (!x.isEmpty && 
                                 x.startsWith("#")))
    // Map each hashtag to a key/value pair of (hashtag, 1) so we can count them up by adding up the values
    //val hashtagKeyValues = hashtags.map(hashtag => (hashtag, 1))
    //val tweetWordsKeyValues = tweetwords.map(tweetwords => (tweetwords, 1))
    val tweetWordsKeyValues = hashtags.map(t => (t, 1))
    
    // Now count them up over a 5 minute window sliding every one second
    // basically its a count over a 5minute window that is updated every one second.
    //val hashtagCounts = hashtagKeyValues.reduceByKeyAndWindow( (x,y) => x + y, (x,y) => x - y, Seconds(300), Seconds(1))
    val tweetwordCounts = tweetWordsKeyValues.reduceByKeyAndWindow( (x,y) => x + y, (x,y) => x - y, Seconds(300), Seconds(1))
    //  You will often see this written in the following shorthand:
    //val hashtagCounts = hashtagKeyValues.reduceByKeyAndWindow( _ + _, _ -_, Seconds(300), Seconds(1))
    
    // Sort the results by the count values in desc order.
    //val sortedResults = hashtagCounts.transform(rdd => rdd.sortBy(x => x._2, false))
    val sortedResults = tweetwordCounts.transform(rdd => rdd.sortBy(x => x._2, false))
    
    // Print the top 10
    sortedResults.print
    //sortedResults.print(30)
    
    // Set a checkpoint directory, and kick it all off
    //ssc.checkpoint("C:/checkpoint/")
    ssc.checkpoint("/Users/tjee/SparkStreamingUdemy/CheckPoint")
    ssc.start()
    ssc.awaitTermination()
  }  
}
