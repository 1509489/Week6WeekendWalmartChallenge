package com.pixelart.week6weekendwalmartchallenge.ui.mainscreen

import com.pixelart.week6weekendwalmartchallenge.base.BasePresenter
import com.pixelart.week6weekendwalmartchallenge.base.BaseView
import com.pixelart.week6weekendwalmartchallenge.model.ApiResponse

interface MainContract {
    interface View: BaseView{
        fun showProducts(apiResponse: ApiResponse)
    }

    interface Presenter: BasePresenter{
        fun getProducts()
        fun dataLoaded(): Boolean
    }
}