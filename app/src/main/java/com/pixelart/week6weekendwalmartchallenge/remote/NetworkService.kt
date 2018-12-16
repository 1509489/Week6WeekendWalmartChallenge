package com.pixelart.week6weekendwalmartchallenge.remote

import com.pixelart.week6weekendwalmartchallenge.common.RELATIVE_URL
import com.pixelart.week6weekendwalmartchallenge.model.ApiResponse
import io.reactivex.Single
import retrofit2.http.GET

interface NetworkService {
    @GET(RELATIVE_URL)
    fun getProducts(): Single<ApiResponse>
}