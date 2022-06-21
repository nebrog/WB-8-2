package com.example.wb_8_2.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class SuperHeroesItem(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("images")
    val image: Image,

    @SerializedName("powerstats")
    val powerstats: Powerstats,
) : Serializable

class Image(
    @SerializedName("xs")
    val xs: String,
    @SerializedName("sm")
    val sm: String,
    @SerializedName("md:")
    val md: String,
    @SerializedName("lg")
    val lg: String,
) : Serializable

class Powerstats(
    @SerializedName("intelligence")
    val intelligence: Int?,
    @SerializedName("strength")
    val strength: Int?,
    @SerializedName("speed")
    val speed: Int?,
    @SerializedName("durability")
    val durability: Int?,
    @SerializedName("power")
    val power: Int?,
    @SerializedName("combat")
    val combat: Int?,
) : Serializable

