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
     * Returns count of number of times movie has been rated with the movie id.  
   * **_PopularMoviesAndNames.scala_** :
     * Returns count of number of times movie has been rated with the movie name.
   * **_MostPopularSuperhero.scala_** :
     * Returns most popular superhero with its total co-occurences.
   * **_MovieSimilarities.scala_** :
     * Returns top 10 similar movies for the movie id passed as an argument eg: 50 for Star Wars (1977). 
     
     
