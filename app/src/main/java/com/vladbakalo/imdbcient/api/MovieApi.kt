package com.vladbakalo.imdbcient.api

import com.vladbakalo.imdbcient.data.dto.MovieDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("/3/movie/popular")
    fun getMovieList(@Query(value = "page") page: Int): Call<BaseListResponse<MovieDto>>

    @GET("/3/search/movie?language=en-US&include_adult=false")
    fun getMovieListBySearchQuery(@Query(value = "page") page: Int, @Query(value = "query") searchQuery: String): Call<BaseListResponse<MovieDto>>

    @GET("/3/movie/{movie_id}?language=en-US")
    fun getDetailedMovieById(@Path("movie_id") movieId: Long): Call<MovieDto>
}