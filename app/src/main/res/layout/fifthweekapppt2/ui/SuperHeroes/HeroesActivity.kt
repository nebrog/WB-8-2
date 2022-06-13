package com.example.fifthweekapppt2.ui.SuperHeroes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fifthweekapppt2.R
import com.example.fifthweekapppt2.data.model.SuperHeroesItem
import com.example.fifthweekapppt2.ui.Hero.HeroActivity

@ExperimentalStdlibApi
class HeroesActivity : AppCompatActivity(),OnHeroClickListener {

    private val adapter = HeroesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heroes)
        supportActionBar?.hide()
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
    }



    override fun onClick(hero: SuperHeroesItem) {
        val intent = Intent(this,HeroActivity::class.java)
        intent.putExtra("hero",hero)
        startActivity(intent)
    }
}