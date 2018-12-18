package com.pixelart.week6weekendwalmartchallenge.ui.mainscreen

import com.pixelart.week6weekendwalmartchallenge.base.BasePresenter
import com.pixelart.week6weekendwalmartchallenge.base.BaseView
import com.pixelart.week6weekendwalmartchallenge.model.ApiResponse
import com.pixelart.week6weekendwalmartchallenge.model.Item

interface MainContract {
    interface View: BaseView{
        fun showProducts(items: List<Item>)
    }

    interface Presenter: BasePresenter{
        fun getProducts()
        fun dataLoaded(): Boolean
    }
}