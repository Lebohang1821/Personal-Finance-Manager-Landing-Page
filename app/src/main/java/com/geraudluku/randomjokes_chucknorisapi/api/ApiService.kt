package com.geraudluku.randomjokes_chucknorisapi.api

import com.geraudluku.randomjokes_chucknorisapi.model.RandomJoke
import com.geraudluku.randomjokes_chucknorisapi.model.Search
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //get a random joke
    @GET("jokes/random")
    suspend fun getRandomJoke(): RandomJoke

    //search for joke
    @GET("jokes/search")
    suspend fun searchJoke(
        @Query("query") query: String
    ): Search

    //get list of categories
    @GET("jokes/categories")
    suspend fun getCategories(): ArrayList<String>

    //get category random joke
    @GET("jokes/random")
    suspend fun getCategoryJoke(
        @Query("category") category: String
    ): RandomJoke
}