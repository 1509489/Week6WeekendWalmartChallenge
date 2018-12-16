package com.pixelart.week6weekendwalmartchallenge.ui.detailscreen

import android.content.Intent
import com.pixelart.week6weekendwalmartchallenge.base.BasePresenter
import com.pixelart.week6weekendwalmartchallenge.base.BaseView
import com.pixelart.week6weekendwalmartchallenge.model.Product

interface DetailContract {
    interface View: BaseView{
        fun showItemDetails()
    }

    interface Presenter:BasePresenter{
        fun getItemDetails(intent: Intent): Product
    }
}