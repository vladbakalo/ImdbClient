package com.vladbakalo.imdbcient.api

import com.google.gson.annotations.SerializedName

class BaseListResponse<T> {
    @SerializedName("page")
    var page: Int = 0

    @SerializedName("results")
    var resultList: List<T> = ArrayList()
}