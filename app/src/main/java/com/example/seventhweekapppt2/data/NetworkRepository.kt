package com.example.seventhweekapppt2.data

import com.example.seventhweekapppt2.data.api.RetrofitServices
import com.example.seventhweekapppt2.data.model.SuperHeroesItem
import com.example.seventhweekapppt2.ui.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class NetworkRepository(

    private val retrofitServices: RetrofitServices

) : Repository {
    override suspend fun loadHero(id: Int): SuperHeroesItem? {
        return withContext(Dispatchers.IO) {
            try {
                val hero = retrofitServices.getSuperHero(id)
                return@withContext hero

            } catch (e: IOException) {
                return@withContext null

            } catch (e: HttpException) {
                return@withContext null
            }
        }
    }
}