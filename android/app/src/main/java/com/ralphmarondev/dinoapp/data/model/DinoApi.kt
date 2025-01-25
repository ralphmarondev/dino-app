package com.ralphmarondev.dinoapp.data.model

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface DinoApi {
    @GET("/random-dino")
    suspend fun getRandomDino(): Dino

    @PUT("/update-dino/{id}")
    suspend fun updateDino(
        @Path("id") id: Int,
        @Body updatedDino: Dino
    ): Response<String>
}