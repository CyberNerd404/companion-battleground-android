package com.cybernerd.companionBattleground.model

import java.io.Serializable

data class Wallpaper(
    val _id: String,
    val createdAt: String,
    val credit: String,
    val image: String,
    val isActive: Boolean,
    val updatedAt: String
): Serializable