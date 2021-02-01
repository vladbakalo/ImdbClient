package com.vladbakalo.imdbcient.data.dto

import com.google.gson.annotations.SerializedName

class MovieDto {

    @SerializedName("id")
    var id: Long = 0L

    @SerializedName("title")
    var title: String = ""

    @SerializedName("popularity")
    var popularity: Float = 0f

    @SerializedName("poster_path")
    var imageUrl: String? = ""

    @SerializedName("overview")
    var overview: String? = ""
}