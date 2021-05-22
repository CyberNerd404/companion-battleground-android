package com.cybernerd.companionBattleground.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomeNewsModel(
    val __v: Int,
    val _id: String,
    val country: String,
    val createdAt: String,
    val description: String,
    val isActive: Boolean,
    val link: String,
    val media: String,
    val newsId: String,
    val publishedDate: String,
    val rights: String,
    val title: String,
    val updatedAt: String
) : Parcelable

