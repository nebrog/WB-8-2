package com.example.wb_8_2.ui.SuperHeroes

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
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

@AndroidEntryPoint
class HeroesActivity : AppCompatActivity(), OnHeroClickListener {

    private val viewModel: HeroesViewModel by viewModels()

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

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
        setupRecycler()
    }

    override fun onClick(hero: SuperHeroesItem) {
        viewModel.onHeroClick(hero)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.onInfoClick()
        return true
    }

    private fun setupRecycler() {
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        val adapter = HeroesAdapter(this)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        viewModel.heroesList.observe(this) { heroes ->
            adapter.setData(heroes)
        }
        viewModel.progressVisibility.observe(this) { isVisible ->
            adapter.setProgressVisibility(isVisible)
        }
    }
}