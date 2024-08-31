package com.example.physicswallahassignment.data.remote.model

import com.google.gson.annotations.SerializedName


data class CharacterListResponse(
    @SerializedName("info") val info: Info? = Info(),
    @SerializedName("results") val results: List<Character> = arrayListOf()
)

//Info
data class Info(

    @SerializedName("count") val count: Int? = null,
    @SerializedName("pages") val pages: Int? = null,
    @SerializedName("next") val next: String? = null,
    @SerializedName("prev") val prev: String? = null
)

//Result
data class Character(

    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("status") val status: String? = null,
    @SerializedName("species") val species: String? = null,
    @SerializedName("type") val type: String? = null,
    @SerializedName("gender") val gender: String? = null,
    @SerializedName("origin") val origin: Origin? = Origin(),
    @SerializedName("location") val location: Location? = Location(),
    @SerializedName("image") val image: String? = null,
    @SerializedName("episode") val episode: ArrayList<String> = arrayListOf(),
    @SerializedName("url") val url: String? = null,
    @SerializedName("created") val created: String? = null
)

