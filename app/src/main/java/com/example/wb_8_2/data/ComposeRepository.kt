package com.example.wb_8_2.data

import com.example.wb_8_2.data.model.SuperHeroesItem
import com.example.wb_8_2.ui.Repository
import javax.inject.Inject

class ComposeRepository  @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository,
) : Repository {

    override suspend fun loadHero(id: Int): SuperHeroesItem? {
        val heroFromPrefs = sharedPreferencesRepository.loadHero(id)
        if (heroFromPrefs == null) {
            val hero = networkRepository.loadHero(id)
            if (hero != null) {
                sharedPreferencesRepository.saveHero(hero)
            }
            return hero
        } else {
            return heroFromPrefs
        }
    }
}