package com.example.katalog.viewModels

import android.os.Parcelable
import com.example.GetCharaterDetailsQuery
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class CharacterParcel(val character: @RawValue GetCharaterDetailsQuery.Character1): Parcelable