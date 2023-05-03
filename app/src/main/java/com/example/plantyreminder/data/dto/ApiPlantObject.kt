package com.example.plantyreminder.data.dto

import com.google.gson.annotations.SerializedName

data class ApiPlantObject (
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("common_name")
    var commonName: String? = null,
    @SerializedName("scientific_name")
    var scientificName: ArrayList<String> = arrayListOf(),
    @SerializedName("other_name")
    var otherName: ArrayList<String> = arrayListOf(),
    @SerializedName("family")
    var family: String? = null,
    @SerializedName("origin")
    var origin: ArrayList<String> = arrayListOf(),
    @SerializedName("type")
    var type: String? = null,
    @SerializedName("dimension")
    var dimension: String? = null,
    @SerializedName("cycle")
    var cycle: String? = null,
    @SerializedName("attracts")
    var attracts: ArrayList<String> = arrayListOf(),
    @SerializedName("propagation")
    var propagation: ArrayList<String> = arrayListOf(),
    @SerializedName("watering")
    var watering: String? = null,
    @SerializedName("sunlight")
    var sunlight: ArrayList<String> = arrayListOf(),
    @SerializedName("maintenance")
    var maintenance: String? = null,
    @SerializedName("care-guides")
    var careGuides: String? = null,
    @SerializedName("soil")
    var soil: ArrayList<String> = arrayListOf(),
    @SerializedName("growth_rate")
    var growthRate: String? = null,
    @SerializedName("drought_tolerant")
    var droughtTolerant: Boolean? = null,
    @SerializedName("salt_tolerant")
    var saltTolerant: Boolean? = null,
    @SerializedName("thorny")
    var thorny: Boolean? = null,
    @SerializedName("invasive")
    var invasive: Boolean? = null,
    @SerializedName("tropical")
    var tropical: Boolean? = null,
    @SerializedName("indoor")
    var indoor: Boolean? = null,
    @SerializedName("care_level")
    var careLevel: String? = null,
    @SerializedName("pest_susceptibility")
    var pestSusceptibility: String? = null,
    @SerializedName("pest_susceptibility_api")
    var pestSusceptibilityApi: String? = null,
    @SerializedName("flowers")
    var flowers: Boolean? = null,
    @SerializedName("flowering_season")
    var floweringSeason: String? = null,
    @SerializedName("flower_color")
    var flowerColor: String? = null,
    @SerializedName("cones")
    var cones: Boolean? = null,
    @SerializedName("fruits")
    var fruits: Boolean? = null,
    @SerializedName("edible_fruit")
    var edibleFruit: Boolean? = null,
    @SerializedName("edible_fruit_taste_profile")
    var edibleFruitTasteProfile: String? = null,
    @SerializedName("fruit_nutritional_value")
    var fruitNutritionalValue: String? = null,
    @SerializedName("fruit_color")
    var fruitColor: ArrayList<String> = arrayListOf(),
    @SerializedName("harvest_season")
    var harvestSeason: String? = null,
    @SerializedName("harvest_method")
    var harvestMethod: String? = null,
    @SerializedName("leaf")
    var leaf: Boolean? = null,
    @SerializedName("leaf_color")
    var leafColor: ArrayList<String> = arrayListOf(),
    @SerializedName("edible_leaf")
    var edibleLeaf: Boolean? = null,
    @SerializedName("edible_leaf_taste_profile")
    var edibleLeafTasteProfile: String? = null,
    @SerializedName("leaf_nutritional_value")
    var leafNutritionalValue: String? = null,
    @SerializedName("cuisine")
    var cuisine: Boolean? = null,
    @SerializedName("endangered")
    var endangered: String? = null,
    @SerializedName("endangered_level")
    var endangeredLevel: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("default_image")
    val imageUrl: ApiResultImageUrl
    )