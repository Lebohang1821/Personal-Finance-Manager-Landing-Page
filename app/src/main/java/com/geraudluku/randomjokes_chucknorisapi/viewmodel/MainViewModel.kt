package com.geraudluku.randomjokes_chucknorisapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.geraudluku.randomjokes_chucknorisapi.api.Repository
import com.geraudluku.randomjokes_chucknorisapi.model.RandomJoke
import com.geraudluku.randomjokes_chucknorisapi.model.Search

class MainViewModel : ViewModel() {

    //get random joke
    val randomJoke = Repository.getRandomJoke()



    //get categories
    val categories = Repository.getCategories()

    //search for joke
    private val _querySearch: MutableLiveData<String> = MutableLiveData()

    val search: LiveData<Search> = Transformations
        .switchMap(_querySearch) {
            Repository.searchJoke(it)
        }

    fun setQuerySearch(query: String) {
        val update = query
        if (_querySearch.value == update)
            return
        _querySearch.value = update
    }

    //display random joke for category
    private val _randomCategoryJoke: MutableLiveData<String> = MutableLiveData()

    val randomCatJoke: LiveData<RandomJoke> = Transformations
        .switchMap(_randomCategoryJoke) {
            Repository.getRandomCategoryJoke(it)
        }

    fun setCategory(query: String) {
        val update = query
        if (_randomCategoryJoke.value == update)
            return
        _randomCategoryJoke.value = update
    }

}