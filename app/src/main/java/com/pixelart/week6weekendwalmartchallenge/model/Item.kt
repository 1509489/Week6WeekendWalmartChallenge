package com.pixelart.week6weekendwalmartchallenge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    val itemId: Int,
    val parentItemId: Int,
    val name: String,
    val salePrice: Double,
    val upc: String,
    val categoryPath: String,
    val shortDescription: String,
    val longDescription: String,
    val brandName: String,
    val thumbnailImage: String,
    val mediumImage: String,
    val largImage: String,
    val productTrackingUrl: String,
    val standardShipRate: Double,
    val marketplace: Boolean,
    val sellerInfo: String,
    val productUrl: String,
    val categoryNode: String,
    val rhid: String,
    val bundle: Boolean,
    val clearance: Boolean,
    val preOrder: Boolean,
    val stock: String,
    val attributes: Attributes,
    val addToCartUrl: String,
    val affiliateAddToCartUrl: String,
    val freeShippingOver35Dollars: Boolean,
    val availableOnline: Boolean
): Parcelable