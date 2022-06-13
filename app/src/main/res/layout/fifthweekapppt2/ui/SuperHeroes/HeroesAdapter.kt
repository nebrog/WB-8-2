package com.example.fifthweekapppt2.ui.SuperHeroes

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fifthweekapppt2.R
import com.example.fifthweekapppt2.data.api.SuperHeroAPI
import com.example.fifthweekapppt2.data.model.SuperHeroesItem
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HeroesAdapter(private val onHeroClickListener: OnHeroClickListener) :
    RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>() {
    companion object {
        const val HEROES_COUNT = 731
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_items, parent, false)
        return HeroesViewHolder(item)
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {

        holder.bindData(position + 1)
    }

    override fun getItemCount(): Int {
        return HEROES_COUNT
    }

    override fun onViewRecycled(holder: HeroesViewHolder) {
        holder.cancelData()
    }

    inner class HeroesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val heroImg = itemView.findViewById<ImageView>(R.id.img_hero)
        private val heroName = itemView.findViewById<TextView>(R.id.hero_name)
        private var call: Call<SuperHeroesItem>? = null
        private var hero: SuperHeroesItem? = null

        init {
            itemView.setOnClickListener {
                val hero = hero
                if (hero != null) {
                    onHeroClickListener.onClick(hero)
                }
            }
        }

        fun bindData(id: Int) {
            Picasso.get().load(R.drawable.placeholder_loading).into(heroImg)
            heroName.text = "..."
            hero = null
            call?.cancel()

            call = SuperHeroAPI.retrofitServices.getSuperHero(id)
            call?.enqueue(object : Callback<SuperHeroesItem> {
                override fun onResponse(
                    call: Call<SuperHeroesItem>?,
                    response: Response<SuperHeroesItem>?
                ) {
                    val item = response?.body()
                    if (item == null) {
                        Picasso.get().load(R.drawable.placeholder_error).into(heroImg)
                    } else {
                        hero = item
                        heroName.text = "${item.id}. ${item.name}"
                        Picasso.get()
                            .load(item.image.url)
                            .error(R.drawable.placeholder_error)
                            .placeholder(R.drawable.placeholder_loading)
                            .into(heroImg)
                    }
                }

                override fun onFailure(call: Call<SuperHeroesItem>?, t: Throwable?) {
                    Log.e("nebrog", t?.message, t)
                    Picasso.get().load(R.drawable.placeholder_error).into(heroImg)
                    heroName.text = "Х_Х"
                }
            })
        }

        fun cancelData() {
            call?.cancel()
        }

    }
}