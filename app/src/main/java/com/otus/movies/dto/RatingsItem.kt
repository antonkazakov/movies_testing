package com.otus.movies.dto

import com.google.gson.annotations.SerializedName

data class RatingsItem(

    @field:SerializedName("Value")
    val value: String? = null,

    @field:SerializedName("Source")
    val source: String? = null
)