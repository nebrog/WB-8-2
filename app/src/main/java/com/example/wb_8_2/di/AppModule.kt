package com.example.wb_8_2.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.wb_8_2.data.ComposeRepository
import com.example.wb_8_2.data.api.RetrofitServices
import com.example.wb_8_2.data.api.SuperHeroAPI
import com.example.wb_8_2.ui.Repository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun gson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun preferences(
        @ApplicationContext
        context: Context
    ): SharedPreferences {
        return context.getSharedPreferences("HERO", Application.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideRepository(repository: ComposeRepository): Repository {
        return repository
    }

    @Provides
    @Singleton
    fun retrofit(): RetrofitServices {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .baseUrl(SuperHeroAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(RetrofitServices::class.java)
    }
}