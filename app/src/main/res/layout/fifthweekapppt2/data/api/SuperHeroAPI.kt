package com.example.fifthweekapppt2.data.api

import com.example.fifthweekapppt2.data.model.SuperHeroesItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


object SuperHeroAPI {
    const val BASE_URL = "https://superheroapi.com/api/3202191630032155/"
    val retrofitServices = create()


    private fun create(): RetrofitServices {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(RetrofitServices::class.java)
    }


}

interface RetrofitServices {
    @GET("{id}")
    fun getSuperHero(@Path("id") id: Int): Call<SuperHeroesItem>

}
