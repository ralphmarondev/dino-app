package com.ralphmarondev.data.model


import kotlinx.serialization.Serializable

@Serializable
data class Dino(
    val id: Int = 0,
    var name: String,
    var description: String,
    var imageUrl: String,
    var isFavorite: Boolean = false,
    var isDeleted: Boolean = false
)