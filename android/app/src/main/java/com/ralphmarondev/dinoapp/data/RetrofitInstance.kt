package com.ralphmarondev.dinoapp.data

import com.ralphmarondev.dinoapp.data.model.DinoApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://192.168.68.104:8080"

    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val dinoApi: DinoApi = getInstance().create(DinoApi::class.java)
}