package com.ralphmarondev.dinoapp.data.model

import retrofit2.http.GET

interface DinoApi {
    @GET("/random-dino")
    suspend fun getRandomDino(): Dino
}