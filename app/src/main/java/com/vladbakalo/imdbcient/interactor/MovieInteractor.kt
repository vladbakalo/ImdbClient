package com.vladbakalo.imdbcient.interactor

import com.vladbakalo.imdbcient.data.model.Movie
import com.vladbakalo.imdbcient.data.repo.MovieRepo

class MovieInteractor(private val movieRepo: MovieRepo) {

    suspend fun getMovieList(page: Int = 1, searchQuery: String = ""): List<Movie>{
        return movieRepo.getMovieList(page, searchQuery)
    }

    suspend fun getDetailedMovieById(movieId: Long): Movie{
        return movieRepo.getDetailedMovieById(movieId)
    }
}