package com.example.wb_8_2.data.api

import com.example.wb_8_2.data.model.SuperHeroesItem
import retrofit2.http.GET
import retrofit2.http.Path


object SuperHeroAPI {

    const val BASE_URL = "https://akabab.github.io/superhero-api/api/"
}

interface RetrofitServices {
    @GET("id/{id}.json")
    suspend fun getSuperHero(@Path("id") id: Int): SuperHeroesItem
}
