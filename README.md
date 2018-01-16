## ScalaSparkExamples
 * scala code for basic applications :
   * **_RatingsCounter.scala_** :   
     * Dataset used is [MovieLens 100K](https://grouplens.org/datasets/movielens/100k/)  
     * 100,000 ratings (1-5) from 943 users on 1682 movies.   
     * Each user has rated at least 20 movies.   
     * _u.data_ :   
       - full u dataset with 100000 ratings by 943 users on 1682 items.
       - each user rated atleast 20 movies.
       - tab separated list of user id | item id | rating | timestamp  
     * _u.info_ :
       - number of users, items and ratings in u dataset.
     * _u.item_ : 
       - Information about the items(movies).
       - This is a tab separated list of movie id | movie title | release date | video release date | IMDb URL | unknown | 
         Action | Adventure | Animation | Children's | Comedy | Crime | Documentary | Drama | Fantasy | Film-Noir | Horror | 
         Musical | Mystery | Romance | Sci-Fi | Thriller | War | Western |  
         The last 19 fields are the genres, a 1 indicates the movie is of that genre, a 0 indicates it is not; movies can be 
         in several genres at once. 
       - The movie ids are the ones used in the u.data dataset.
     * _u.genre_ :
       - list of movie genres.
     * _u.user_ : 
       - Demographic information about the users. This is a tab separated list of user id | age | gender | occupation | zip 
         code
       - user ids are the ones used in u.data dataset.
     * _u.occupation_ :
       - list of the occupations.       
     * We finally return count of each star rating that exists in MovieLens 100K dataset.  
   
   * **_FriendsByAge.scala_** :
     * Report number of friends by age in a social network.  
   * **_MinTemperatures.scala_** :     
     * Report min temparature by weather station after parsing the input data.  
   * **_WordCountBetterSorted.scala_** : 
     * Returns count of words appearing in a book in a sorted manner.    
   * **_TotalSpentByCustomerSorted.scala_** : 
     * Returns total amount spent per customer in synthetic e-commerce data.   
   * **_PopularMovies.scala_** : 
     * Returns count of number of times movie has been rated alongside the movie id.  
   * **_PopularMoviesAndNames.scala_** :
     * Returns count of number of times movie has been rated alongside the movie name.
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
     
