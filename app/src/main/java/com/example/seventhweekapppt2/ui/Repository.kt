package com.example.seventhweekapppt2.ui

import com.example.seventhweekapppt2.data.model.SuperHeroesItem

interface Repository {
    suspend fun loadHero(id: Int): SuperHeroesItem?
}
