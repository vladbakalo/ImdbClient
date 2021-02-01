package com.vladbakalo.imdbcient.data.model

import com.vladbakalo.imdbcient.common.Constants.IMAGE_PATH
import com.vladbakalo.imdbcient.data.dto.MovieDto

data class Movie(val id: Long, val title: String, val imageUrl: String?, val popularity: Float, val overview: String?) {

    constructor(movieDto: MovieDto): this(movieDto.id, movieDto.title, movieDto.imageUrl, movieDto.popularity, movieDto.overview)

    fun getFormattedImageUrl(): String{
        return IMAGE_PATH + imageUrl
    }
}