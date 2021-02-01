package com.vladbakalo.imdbcient.ui.movie_list

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vladbakalo.imdbcient.base.BaseViewModel
import com.vladbakalo.imdbcient.data.model.Movie
import com.vladbakalo.imdbcient.interactor.MovieInteractor
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MovieListViewModel @Inject constructor(private val movieInteractor: MovieInteractor): BaseViewModel() {

    val movieListLiveData = MutableLiveData<List<Movie>>(ArrayList())

    private var searchQuery: String = ""

    private val searchHandler = Handler(Looper.getMainLooper()){ message ->
        searchQuery = message.obj as String
        loadMovies(searchText = searchQuery)
        return@Handler true
    }

    init {
        loadMovies()
    }

    fun onLoadMoreMovies(page: Int){
        loadMovies(page, searchQuery)
    }

    fun onSearchTextChange(searchText: String){
        searchHandler.removeMessages(SEARCH_MSG)
        searchHandler.sendMessageDelayed(searchHandler.obtainMessage(SEARCH_MSG, searchText), TimeUnit.SECONDS.toMillis(1))
    }

    private fun loadMovies(page: Int = 1, searchText: String = ""){
        if (page == 1){
            movieListLiveData.postValue(ArrayList())
        }

        viewModelScope.launch {

            val movieList = async {movieInteractor.getMovieList(page, searchText)}

            val resultList = movieList.await()
            val combinedList = movieListLiveData.value!!.toMutableList()
            combinedList.addAll(resultList)

            movieListLiveData.postValue(combinedList)
        }
    }

    companion object{
        private const val SEARCH_MSG = 1
    }
}