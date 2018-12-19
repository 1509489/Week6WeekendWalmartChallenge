package com.pixelart.week6weekendwalmartchallenge.model

data class ApiResponse(
    val category: String,
    val format: String,
    val nextPage: String,
    val totalPages: String,
    var items: List<Item>? = null
)