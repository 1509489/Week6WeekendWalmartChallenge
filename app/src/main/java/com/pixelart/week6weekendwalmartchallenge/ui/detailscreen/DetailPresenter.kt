package com.pixelart.week6weekendwalmartchallenge.ui.detailscreen

import android.content.Intent
import com.pixelart.week6weekendwalmartchallenge.model.Product
import javax.inject.Inject

class DetailPresenter @Inject constructor(private val view: DetailContract.View): DetailContract.Presenter {

    override fun getItemDetails(intent: Intent): Product = intent.getParcelableExtra("product")

    override fun onResume() {

    }

    override fun onDestroy() {

    }
}