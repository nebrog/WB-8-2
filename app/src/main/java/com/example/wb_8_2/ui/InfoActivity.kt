package com.example.wb_8_2.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wb_8_2.R
import com.example.wb_8_2.data.model.SuperHeroesItem
import com.example.wb_8_2.ui.Hero.HeroActivity
import com.github.terrakok.cicerone.androidx.ActivityScreen

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
    }
    class InfoScreen : ActivityScreen {
        override fun createIntent(context: Context): Intent {
            val intent = Intent(context, InfoActivity::class.java)
            return intent
        }
    }
}