package com.example.physicswallahassignment.data.remote.model

import com.google.gson.annotations.SerializedName

data class CharacterDetailResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("status") val status: String? = null,
    @SerializedName("species") val species: String? = null,
    @SerializedName("type") val type: String? = null,
    @SerializedName("gender") val gender: String? = null,
    @SerializedName("origin") val origin: Origin? = Origin(),
    @SerializedName("location") val location: Location? = Location(),
    @SerializedName("image") val image: String? = null,
    @SerializedName("episode") val episode: List<String>? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("created") val created: String? = null
)