package com.example.wb_8_2.ui

import com.example.wb_8_2.data.model.SuperHeroesItem

interface Repository {
    suspend fun loadHero(id: Int): SuperHeroesItem?
}
