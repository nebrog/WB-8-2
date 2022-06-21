package com.example.wb_8_2.ui.SuperHeroes

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wb_8_2.App
import com.example.wb_8_2.data.model.SuperHeroesItem
import com.example.wb_8_2.ui.Hero.HeroActivity
import com.example.wb_8_2.R
import com.example.wb_8_2.ui.InfoActivity
import com.github.terrakok.cicerone.androidx.AppNavigator

@ExperimentalStdlibApi
class HeroesActivity : AppCompatActivity(), OnHeroClickListener {


    private val adapter by lazy {
        HeroesAdapter(lifecycleScope, (applicationContext as App).repository, this)
    }

    override fun onResume() {
        super.onResume()
        (applicationContext as App).navigatorHolder.setNavigator(AppNavigator(this,-1))
    }

    override fun onPause() {
        super.onPause()
        (applicationContext as App).navigatorHolder.removeNavigator()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heroes)
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
    }


    override fun onClick(hero: SuperHeroesItem) {
        (applicationContext as App).router.navigateTo(HeroActivity.HeroScreen(hero))

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        (applicationContext as App).router.navigateTo(InfoActivity.InfoScreen())
        return super.onOptionsItemSelected(item)
    }

}