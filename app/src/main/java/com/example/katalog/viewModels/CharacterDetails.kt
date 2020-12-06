package com.example.katalog.viewModels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterDetails(
    val name: String?,
    val description: String?,
    val cover: String?,
    val category: String?,
    val color: String?,
): Parcelable
