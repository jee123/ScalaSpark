## ScalaSparkExamples
 * scala code for basic applications :
   * **_RatingsCounter.scala_** :   
     * Return tuple of (rating, count) which is count of users for each rating. 
     * Dataset used is [MovieLens 100K](https://grouplens.org/datasets/movielens/100k/).    
     * 100,000 ratings (1-5) from 943 users on 1682 movies.     
     * Load rating data into RDD.  
     * Using map method convert each line to string, split on tab and extract third field.  
     * Use countByValue to generate a map of (rating, count).  
     * Print the sorted map based on rating as final result.   
      
   * **_FriendsByAge.scala_** :
     * Return tuple of (age, numberofFriends) which is average number of friends by age in social network.
     * Load each line of input into RDD.  
     * Define function parseLine that splits line of input into (age, numFriends) tuples.  
     * Create tuple of (age, numFriends) using function parseLine on the RDD.  
     * Using mapValues convert each numFriends value to tuple of (numFriends, 1). Now reduceByKey to sum up total numFriends and 
       1's for each age.  
     * Use tuple (age, (totalnumFriends, totalInstances)) to get averageFriends by age as (x._1 /x._2).     
    
   * **_MinTemperatures.scala_** :     
     * Report min temparature by weather station after parsing the input data.    
     
   * **_WordCountBetterSorted.scala_** : 
     * Return tuple of (count, word) where count is sorted occurence of frequency of the word.  
     * Load each line of book into an RDD.  
     * Split using a regular expression that extracts words.  
     * Transform all words to lowercase.  
     * To count occurence of each word map lowercase words like lowercaseWords.map(x => (x , 1)) and then reduceByKey.  
     * Flip (word,count) to (count, word) and then sort by key.  
     
   * **_TotalSpentByCustomerSorted.scala_** : 
     * Return tuple of (amountSpent, customerId) where amountSpent is sorted amount of money spent by person with the given customerId.  
     * Map RDD, holding the input data, to (customerID, amountSpent) tuples.  
     * Using reduceByKey get the totalAmount spent by each customer.  
     * Flip this RDD using x => (x._2, x._1) and then sort by key.
     * Use collect() on this RDD and print the results.  
     
   * **_PopularMovies.scala_** : 
     * Return tuple of (count, movieId) where count is sorted occurence of frequency of the movie with id given by movieId.   
     * Load input data into RDD that is mapped to (movieID, 1) tuple.
     * To get movieCount use reduceByKey on this RDD like movies.reduceByKey((x,y) => x + y)
     * Flip (movieID, count) tuple to (count, movieID) and then sort by key.
     * Use collect() on this RDD and print results.

   * **_PopularMoviesAndNames.scala_** :
     * Return tuple of (movieName, count) where count is sorted occurence of frequency of the movie with name given by movieName.   
     * Using given input of u.item create map of Ints to String.   
     * Broadcast variable of the ID -> movie name map.  
     * Load input of u.data into RDD.
     * Map this RDD to (movieID, 1) tuple .  
     * Using reduceByKey count all 1's for each movieID.  
     * Flip (movieID, count) to (count, movieID) and sort by key.  
     * Map this sortedRDD containing (count, movieID) to (movieName, count) using the broadcast variable.   
       This is done as sortedRDD.map(x => (broadcastVar.value(x._2), x._1)) .   
     * Use collect() on this new RDD of (movieName, count) and print results.  
     
   * **_MostPopularSuperhero.scala_** :
     * Returns most popular superhero with its total co-occurences.
   * **_MovieSimilarities.scala_** :
     * Returns top 10 similar movies for the movie id passed as an argument eg: 50 for Star Wars (1977).   
   * **_MovieRecommendationsALS_** :
     * This recommender model is built using Alternating Least Squares in Spark MLlib.
     * Returns top 10 similar movies for the movie id passed as an argument.  
   * **_MovieSimilarities1M.scala_** :
     * Use an Amazon S3 bucket to store MovieSimilarities1M.jar, ml-1m/movies.dat and ml-1m/ratings.dat inside movieJarDir.
     * Use Amazon EMR to create cluster and subsequently create EC2 key pairs if you dont have one already.
     * Selected 3 m4.xlarge instances to have 1 master and 2 slaves.
     * Submit jar by using spark-submit --executor-memory 1g MovieSimilarities1M.jar 260   
       where 260 is id for Star Wars in the 1M dataset.  
     * Returns top 50 similar movies.
     * Terminate cluster.
   * **_SparkSQL.scala_** :  
     * Convert input csv to a dataset using Person case class.
     * Infer schema from dataset, register dataset as a table, and run SparkSql queries on the table.  
   * **_DataFrames.scala_** :
     * Convert input csv to a dataset using Person case class.  
     * Infer schema from dataset and run SparkSql queries using the dataset.  
     * Compared to SpqrkSQL class we don't register a table here.  
   * **_PopularMoviesDataSets.scala_** :
     * Similar logic to PopularMovies.scala but uses SparkSql query run on dataset. 
     * We return count of number of times movie has been rated alongside the movie id as intermediate result and count of 
       movie occurence with the movie name as final result.    
   * **_LinearRegression.scala_** : 
     * Create training and testing data using the input.
     * Convert training and testing data to LabeledPoints for MLlib.
     * Build LinearRegression model using LinearRegressionWithSGD().  
     * Run the create model on training data and then zip the result with labels from test data.  
     * Store zipped results as predictionAndLabel. 
     * Print predictionAndLabel to see the predicted data point and the actual data point.     
   * **_LinearRegressionDataFrame.scala_** :
     * Create an RDD from the given input.
     * Convert this RDD to a DataFrame passing columnNames as a sequence.  
     * Randomly split this DataFrame into testingDataFrame and trainingDataFrame.
     * Build a LinearRegression model using LinearRegression().
     * Fit the model on trainingDataFrame.
     * Generate predictions using this model for all features in testingDataFrame and also transform the testingDataFrame by    
       adding a prediction column to its earlier columns of "label" and "feature".    
     * Select prediction and label column from the Dataframe created in the step above and transform it to an RDD,   
       mapping values in both columns to Double.  
     * Returning this RDD would show the predicted and actual data points.  
   * **_PopularHashtagsSparkStreaming.scala_** :
     * Saved twitter credentials in twitter.txt.  
     * Created a Spark Streaming context and then DStream from TwitterUtils using that streaming context.  
     * Extracted text of each status update into DStream using map().  
     * Expand each word in tweet into a new DStream.  
     * Used map to replace anything not a character with space and then trimmed and transformed the words into lowercase. 
       Further, used filter to get only the words starting with "#" and are non empty and stored them as hashtags.    
     * In DStream hashtags, map each key to a key/value pair like hashtags.map(t => (t,1)) so they may be counted during 
       reduce operation later.  
     * Using reduceByKeyAndWindow we maintain a sum of the count based on the key value over a window that is 5min long and 
       updated every 1 second.  
     * Finally, sort the resulting DStream based on the count in descending order and print.  
     * Checkpoint direcory put in place to restart job if it fails.  
     
