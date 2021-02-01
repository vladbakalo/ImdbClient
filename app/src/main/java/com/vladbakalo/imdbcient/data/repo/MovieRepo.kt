package com.vladbakalo.imdbcient.data.repo

import com.vladbakalo.imdbcient.api.BaseListResponse
import com.vladbakalo.imdbcient.api.MovieApi
import com.vladbakalo.imdbcient.data.dto.MovieDto
import com.vladbakalo.imdbcient.data.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MovieRepo(private val movieApi: MovieApi) {

    suspend fun getMovieList(page: Int = 1, searchQuery: String = ""): List<Movie>{
        return if (searchQuery.isNotBlank())
            getMovieListBySearchQuery(page, searchQuery)
        else
            getMainMovieList(page)
    }

    suspend fun getDetailedMovieById(movieId: Long): Movie {
        return suspendCoroutine { continuation ->
            movieApi.getDetailedMovieById(movieId).enqueue(object : Callback<MovieDto>{
                override fun onResponse(
                    call: Call<MovieDto>,
                    response: Response<MovieDto>
                ) {
                    val data = Movie(response.body()!!)
                    continuation.resume(data)
                }

                override fun onFailure(call: Call<MovieDto>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

            })
        }
    }

    private suspend fun getMainMovieList(page: Int = 1): List<Movie>{
        return suspendCoroutine { continuation ->
            movieApi.getMovieList(page).enqueue(object : Callback<BaseListResponse<MovieDto>>{
                override fun onResponse(
                    call: Call<BaseListResponse<MovieDto>>,
                    response: Response<BaseListResponse<MovieDto>>
                ) {
                    val data = response.body()?.resultList!!.map { Movie(it) }
                    continuation.resume(data)
                }

                override fun onFailure(call: Call<BaseListResponse<MovieDto>>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

            })
        }
    }

    private suspend fun getMovieListBySearchQuery(page: Int = 1, searchQuery: String = ""): List<Movie>{
        return suspendCoroutine { continuation ->
            movieApi.getMovieListBySearchQuery(page, searchQuery).enqueue(object : Callback<BaseListResponse<MovieDto>>{
                override fun onResponse(
                    call: Call<BaseListResponse<MovieDto>>,
                    response: Response<BaseListResponse<MovieDto>>
                ) {
                    val data = response.body()?.resultList!!.map { Movie(it) }
                    continuation.resume(data)
                }

                override fun onFailure(call: Call<BaseListResponse<MovieDto>>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

            })
        }
    }
}