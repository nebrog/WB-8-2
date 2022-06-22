package com.example.wb_8_2.ui.SuperHeroes

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wb_8_2.R
import com.example.wb_8_2.data.model.SuperHeroesItem
import com.example.wb_8_2.ui.Hero.HeroActivity
import com.example.wb_8_2.ui.InfoActivity
import com.example.wb_8_2.ui.Repository
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalStdlibApi
@AndroidEntryPoint
class HeroesActivity : AppCompatActivity(), OnHeroClickListener {

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val adapter by lazy {
        HeroesAdapter(lifecycleScope, repository, this)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(AppNavigator(this, -1))
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heroes)
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
    }


    override fun onClick(hero: SuperHeroesItem) {
        router.navigateTo(HeroActivity.HeroScreen(hero))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        router.navigateTo(InfoActivity.InfoScreen())
        return super.onOptionsItemSelected(item)
    }

}