package com.example.seventhweekapppt2

import android.app.Application
import android.content.SharedPreferences
import com.example.seventhweekapppt2.data.ComposeRepository
import com.example.seventhweekapppt2.data.NetworkRepository
import com.example.seventhweekapppt2.data.SharedPreferencesRepository
import com.example.seventhweekapppt2.data.api.SuperHeroAPI
import com.example.seventhweekapppt2.ui.Repository
import com.google.gson.Gson

class App : Application() {


    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()
        val preferense:SharedPreferences = getSharedPreferences("HERO", MODE_PRIVATE)
        val gson = Gson()
        val networkRepository = NetworkRepository(SuperHeroAPI.retrofitServices)
        val sharedPreferencesRepository = SharedPreferencesRepository(preferense,gson)
        repository = ComposeRepository(networkRepository, sharedPreferencesRepository)


    }
}