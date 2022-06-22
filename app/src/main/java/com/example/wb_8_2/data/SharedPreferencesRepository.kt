package com.example.wb_8_2.data

import android.content.SharedPreferences
import com.example.wb_8_2.data.model.SuperHeroesItem
import com.example.wb_8_2.ui.Repository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SharedPreferencesRepository @Inject constructor(
    private val preferences: SharedPreferences,
    private val gson: Gson,
) : Repository {

    suspend fun saveHero(hero: SuperHeroesItem) {
        withContext(Dispatchers.IO) {
            val json = gson.toJson(hero)
            val editor = preferences.edit()
            editor.putString(hero.id.toString(), json)
            editor.apply()
        }
    }

    override suspend fun loadHero(id: Int): SuperHeroesItem? {
        return withContext(Dispatchers.IO) {
            val json = preferences.getString(id.toString(), null)
            val hero = gson.fromJson(json, SuperHeroesItem::class.java)
            return@withContext hero
        }
    }
}