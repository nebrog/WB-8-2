package com.example.wb_8_2.ui.SuperHeroes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wb_8_2.data.model.Image
import com.example.wb_8_2.data.model.Powerstats
import com.example.wb_8_2.data.model.SuperHeroesItem
import com.example.wb_8_2.ui.Hero.HeroActivity
import com.example.wb_8_2.ui.InfoActivity
import com.example.wb_8_2.ui.Repository
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel @Inject constructor(
    private val router: Router,
    private val repository: Repository,
) : ViewModel() {

    companion object {
        const val HEROES_COUNT = 731
    }

    val heroesList = MutableLiveData<List<SuperHeroesItem>>(emptyList())
    val progressVisibility = MutableLiveData<Boolean>(true)

    init {
        startLoadHeroes()
    }

    fun onHeroClick(hero: SuperHeroesItem) {
        router.navigateTo(HeroActivity.HeroScreen(hero))
    }

    fun onInfoClick() {
        router.navigateTo(InfoActivity.InfoScreen())
    }

    private fun startLoadHeroes() {
        val list = ArrayList<SuperHeroesItem>(731)
        viewModelScope.launch {
            for (id in 1..HEROES_COUNT) {
                val hero = repository.loadHero(id)
                if (hero != null) {
                    list.add(hero)
                } else {
                    val invalidHero = createInvalidHero(id)
                    list.add(invalidHero)
                }
                heroesList.value = list.toList()
            }
            progressVisibility.value = false
        }
    }

    private fun createInvalidHero(id: Int): SuperHeroesItem {
        return SuperHeroesItem(
            id = id,
            name = "X_X",
            image = Image("url", "url", "url", "url"),
            powerstats = Powerstats(null, null, null, null, null, null)
        )
    }
}
