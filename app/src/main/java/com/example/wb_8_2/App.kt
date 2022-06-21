package com.example.wb_8_2

import android.app.Application
import android.content.SharedPreferences
import com.example.wb_8_2.data.ComposeRepository
import com.example.wb_8_2.data.NetworkRepository
import com.example.wb_8_2.data.SharedPreferencesRepository
import com.example.wb_8_2.data.api.SuperHeroAPI
import com.example.wb_8_2.ui.Repository
import com.google.gson.Gson
import com.github.terrakok.cicerone.Cicerone

class App : Application() {




    lateinit var repository: Repository
    private val cicerone = Cicerone.create()
    val router = cicerone.router
    val navigatorHolder = cicerone.getNavigatorHolder()

    override fun onCreate() {
        super.onCreate()
        val preferense:SharedPreferences = getSharedPreferences("HERO", MODE_PRIVATE)
        val gson = Gson()
        val networkRepository = NetworkRepository(SuperHeroAPI.retrofitServices)
        val sharedPreferencesRepository = SharedPreferencesRepository(preferense,gson)
        repository = ComposeRepository(networkRepository, sharedPreferencesRepository)

    }
}