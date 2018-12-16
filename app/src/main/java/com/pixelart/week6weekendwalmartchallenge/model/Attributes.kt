package com.pixelart.week6weekendwalmartchallenge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Attributes(
    val mainimageurl: String,
    val prodClassType: String,
    val productUrlText: String
):Parcelable