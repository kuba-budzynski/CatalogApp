package com.example.katalog.viewModels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageParcel(val name:String? , val url: String?): Parcelable