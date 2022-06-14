package com.example.seventhweekapppt2.data

import com.example.seventhweekapppt2.data.model.SuperHeroesItem
import com.example.seventhweekapppt2.ui.Repository

class ComposeRepository(
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