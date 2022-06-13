package com.example.seventhweekapppt2.data

import com.example.seventhweekapppt2.data.api.RetrofitServices
import com.example.seventhweekapppt2.data.model.SuperHeroesItem
import com.example.seventhweekapppt2.ui.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.text.ParseException

class NetworkRepository(

    private val retrofitServices: RetrofitServices

) : Repository {
    override suspend fun loadHero(id: Int): SuperHeroesItem? {
        return withContext(Dispatchers.IO) {
            try {
                val hero = retrofitServices.getSuperHero(id)
                return@withContext hero.body()

            } catch (e: IOException) {
                return@withContext null

            } catch (e: ParseException) {
                return@withContext null

            }

        }
    }
}