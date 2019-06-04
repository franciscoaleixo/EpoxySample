package com.fa.epoxysample.others.model

data class Application @JvmOverloads constructor(
    val name: String? = null,
    val icon: String? = null,
    val rating: Float = -1f,
    val downloads: Int = -1,
    val packageName: String? = null,
    val appId: Long = -1L,
    val tag: String = "",
    val hasBilling: Boolean = false,
    val featureGraphic: String? = null,
    val clickUrl: String? = null,
    val downloadUrl: String? = null,
    val reward: Float? = null
)
