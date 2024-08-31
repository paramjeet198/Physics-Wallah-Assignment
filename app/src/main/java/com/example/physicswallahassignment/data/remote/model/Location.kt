package com.example.physicswallahassignment.data.remote.model

import com.google.gson.annotations.SerializedName

//Location
data class Location(
    @SerializedName("name") val name: String? = null,
    @SerializedName("url") val url: String? = null
)