package com.ralphmarondev.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Dino(
    val name: String,
    val description: String,
    val imageUrl: String
)