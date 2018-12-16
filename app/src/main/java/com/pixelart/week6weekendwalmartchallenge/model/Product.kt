package com.pixelart.week6weekendwalmartchallenge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val name: String,
    val price: Double,
    val stock: String,
    val shortDescription: String? = null,
    val longDescription: String? = null,
    val sellerInfo: String? = null,
    val deliveryInfo: Boolean,
    val preOrder: Boolean,
    val image: String

):Parcelable