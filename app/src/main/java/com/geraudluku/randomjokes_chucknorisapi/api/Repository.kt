package com.geraudluku.randomjokes_chucknorisapi.api

import android.util.Log
import androidx.lifecycle.LiveData
import com.geraudluku.randomjokes_chucknorisapi.model.RandomJoke
import com.geraudluku.randomjokes_chucknorisapi.model.Search
import kotlinx.coroutines.*

object Repository {
    var job: CompletableJob? = null
    val TAG = "Repository"

    //get a randpm joke
    fun getRandomJoke(): LiveData<RandomJoke> {
        job = Job()
        return object : LiveData<RandomJoke>() {
            override fun onActive() { //when this method is called do something
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(Dispatchers.IO + theJob).launch {//get trending photos on the background thread
                        try {
                            val randomJoke: RandomJoke =
                                RetrofitBuilder.apiService.getRandomJoke()

                            withContext(Dispatchers.Main) {
                                //set value on the main thread
                                value = randomJoke
                                Log.d("Random Joke", randomJoke.toString())
                                theJob.complete()
                            }

                        } catch (e: Throwable) {
                            Log.d(TAG, e.message.toString())
                        }
                    }
                }
            }
        }
    }

    //search for a joke using free text
    fun searchJoke(query: String): LiveData<Search> {
        job = Job()
        return object : LiveData<Search>() {
            override fun onActive() { //when this method is called do something
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(Dispatchers.IO + theJob).launch {//get trending photos on the background thread
                        try {
                            val search: Search =
                                RetrofitBuilder.apiService.searchJoke(query)

                            withContext(Dispatchers.Main) {
                                //set value on the main thread
                                value = search
                                Log.d("Search", search.toString())
                                theJob.complete()
                            }

                        } catch (e: Throwable) {
                            Log.d(TAG, e.message.toString())
                        }
                    }
                }
            }
        }
    }

    //get a list of categories
    fun getCategories(): LiveData<ArrayList<String>> {
        job = Job()
        return object : LiveData<ArrayList<String>>() {
            override fun onActive() { //when this method is called do something
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(Dispatchers.IO + theJob).launch {//get trending photos on the background thread
                        try {
                            val categories =
                                RetrofitBuilder.apiService.getCategories()

                            withContext(Dispatchers.Main) {
                                //set value on the main thread
                                value = categories
                                Log.d("Search", categories.toString())
                                theJob.complete()
                            }

                        } catch (e: Throwable) {
                            Log.d(TAG, e.message.toString())
                        }
                    }
                }
            }
        }
    }

    //get a random joke from category
    fun getRandomCategoryJoke(
        category: String
    ): LiveData<RandomJoke> {
        job = Job()
        return object : LiveData<RandomJoke>() {
            override fun onActive() { //when this method is called do something
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(Dispatchers.IO + theJob).launch {//get trending photos on the background thread
                        try {
                            val randomJoke: RandomJoke =
                                RetrofitBuilder.apiService.getCategoryJoke(category)

                            withContext(Dispatchers.Main) {
                                //set value on the main thread
                                value = randomJoke
                                Log.d("Random Joke", randomJoke.toString())
                                theJob.complete()
                            }

                        } catch (e: Throwable) {
                            Log.d(TAG, e.message.toString())
                        }
                    }
                }
            }
        }
    }

    fun cancelJobs() {
        job?.cancel()
    }

}