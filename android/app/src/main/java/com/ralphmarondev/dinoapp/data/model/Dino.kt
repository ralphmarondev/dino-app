package com.ralphmarondev.dinoapp.data.model

data class Dino(
    val id: Int = 0,
    var name: String,
    var description: String,
    var imageUrl: String,
    var isFavorite: Boolean = false
)