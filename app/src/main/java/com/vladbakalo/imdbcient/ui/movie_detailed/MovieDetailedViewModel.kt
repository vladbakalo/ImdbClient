package com.vladbakalo.imdbcient.ui.movie_detailed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladbakalo.imdbcient.base.BaseViewModel
import com.vladbakalo.imdbcient.data.model.Movie
import com.vladbakalo.imdbcient.interactor.MovieInteractor
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailedViewModel @Inject constructor(private val movieInteractor: MovieInteractor): BaseViewModel() {

    val movieLiveData = MutableLiveData<Movie>()

    fun setMovieId(movieId: Long){
        viewModelScope.launch {

            val movie = async {movieInteractor.getDetailedMovieById(movieId)}

            movieLiveData.postValue(movie.await())
        }
    }
}