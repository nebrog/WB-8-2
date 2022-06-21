package com.example.wb_8_2.ui.Hero

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.wb_8_2.R
import com.example.wb_8_2.data.model.SuperHeroesItem
import com.example.wb_8_2.ui.SuperHeroes.HeroesActivity
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.squareup.picasso.Picasso

class HeroActivity : AppCompatActivity() {
    companion object {
        const val HERO_ARG = "hero"
    }

    private val heroItem: SuperHeroesItem by lazy { intent.getSerializableExtra(HERO_ARG) as SuperHeroesItem }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero)
        supportActionBar?.hide()
        val img = findViewById<ImageView>(R.id.hero_img_fullscreen)
        val name = findViewById<TextView>(R.id.hero_name_fullscreen)
        val intelligence = findViewById<TextView>(R.id.hero_intelligence_fullscreen)
        val strength = findViewById<TextView>(R.id.hero_strength_fullscreen)
        val speed = findViewById<TextView>(R.id.hero_speed_fullscreen)
        val durability = findViewById<TextView>(R.id.hero_durability_fullscreen)
        val power = findViewById<TextView>(R.id.hero_power_fullscreen)
        val combat = findViewById<TextView>(R.id.hero_combat_fullscreen)
        Picasso.get().load(heroItem.image.sm).into(img)
        name.text = getString(R.string.name, heroItem.name)
        intelligence.text =
            "${getString(R.string.intelligence)} ${heroItem.powerstats.intelligence}"
        strength.text = "${getString(R.string.strength)} ${heroItem.powerstats.strength}"
        speed.text = "${getString(R.string.speed)} ${heroItem.powerstats.speed}"
        durability.text = "${getString(R.string.durability)} ${heroItem.powerstats.durability}"
        power.text = "${getString(R.string.power)} ${heroItem.powerstats.power}"
        combat.text = "${getString(R.string.combat)} ${heroItem.powerstats.combat}"
    }
    class HeroScreen (private val item: SuperHeroesItem): ActivityScreen{

        override fun createIntent(context: Context): Intent {
            val intent = Intent(context, HeroActivity::class.java)
            intent.putExtra(HERO_ARG,item)
            return intent
        }
    }
}