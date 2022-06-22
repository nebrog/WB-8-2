package com.example.wb_8_2.ui.SuperHeroes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wb_8_2.R
import com.example.wb_8_2.data.model.SuperHeroesItem
import com.squareup.picasso.Picasso
import kotlin.math.sign


class HeroesAdapter(
    private val onHeroClickListener: OnHeroClickListener,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_HERO = 0
        private const val VIEW_TYPE_PROGRESS = 1
    }

    private val heroItems = ArrayList<SuperHeroesItem>()
    private var isProgressVisible = true

    fun setData(newHeroItem: List<SuperHeroesItem>) {
        val diffUtil = HeroesDiffCallback(heroItems, newHeroItem, isProgressVisible, isProgressVisible)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        diffResult.dispatchUpdatesTo(this)
        heroItems.clear()
        heroItems.addAll(newHeroItem)
    }

    fun setProgressVisibility(isVisible: Boolean) {
        val diffUtil = HeroesDiffCallback(heroItems, heroItems, isProgressVisible, isVisible)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        diffResult.dispatchUpdatesTo(this)
        isProgressVisible = isVisible
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_PROGRESS) {
            val item = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_progress, parent, false)
            return ProgressViewHolder(item)
        } else {
            val item = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_items, parent, false)
            return HeroesViewHolder(item)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position < heroItems.size) {
            val hero = heroItems.get(position)
            (holder as? HeroesViewHolder)?.bindData(hero)
        }
    }

    override fun getItemCount(): Int {
        val heroSize = heroItems.size
        if (isProgressVisible) {
            return heroSize + 1
        } else {
            return heroSize
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position < heroItems.size) {
            return VIEW_TYPE_HERO
        } else {
            return VIEW_TYPE_PROGRESS
        }
    }

    inner class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class HeroesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val heroImg = itemView.findViewById<ImageView>(R.id.img_hero)
        private val heroName = itemView.findViewById<TextView>(R.id.hero_name)

        fun bindData(hero: SuperHeroesItem) {
            Picasso.get()
                .load(hero.image.sm)
                .error(R.drawable.placeholder_error)
                .placeholder(R.drawable.placeholder_loading)
                .into(heroImg)

            heroName.text = "${hero.id}. ${hero.name}"

            itemView.setOnClickListener {
                onHeroClickListener.onClick(hero)
            }
        }
    }

    class HeroesDiffCallback(
        private val oldList: List<SuperHeroesItem>,
        private val newList: List<SuperHeroesItem>,
        private val isOldProgress: Boolean,
        private val isNewProgress: Boolean,
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            val additionalSize = if (isOldProgress) 1 else 0
            return oldList.size + additionalSize
        }

        override fun getNewListSize(): Int {
            val additionalSize = if (isNewProgress) 1 else 0
            return newList.size + additionalSize
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            if (oldItemPosition < oldList.size && newItemPosition < newList.size) {
                return oldList[oldItemPosition].id == newList[newItemPosition].id
            } else {
                return true
            }
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            if (oldItemPosition < oldList.size && newItemPosition < newList.size) {
                return oldList[oldItemPosition].id == newList[newItemPosition].id
            } else {
                return true
            }
        }
    }
}
