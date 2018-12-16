package com.pixelart.week6weekendwalmartchallenge.ui.detailscreen

import android.annotation.SuppressLint
import android.os.Bundle
import com.pixelart.week6weekendwalmartchallenge.base.BaseActivity
import com.pixelart.week6weekendwalmartchallenge.common.GlideApp
import com.pixelart.week6weekendwalmartchallenge.databinding.ActivityDetailBinding
import com.pixelart.week6weekendwalmartchallenge.di.ApplicationModule
import com.pixelart.week6weekendwalmartchallenge.di.DaggerApplicationComponent
import com.pixelart.week6weekendwalmartchallenge.di.NetworkModule
import javax.inject.Inject

class DetailActivity : BaseActivity<DetailContract.Presenter>(), DetailContract.View {

    @Inject
    lateinit var presenter: DetailContract.Presenter
    @Inject
    lateinit var detailBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showItemDetails()
    }

    @SuppressLint("SetTextI18n")
    override fun showItemDetails() {
        val product = presenter.getItemDetails(intent)
        detailBinding.apply {
            tvName.text = product.name
            tvShortDesc.text = product.shortDescription
            tvDescription.text = product.longDescription
            tvPrice.text = "Price: $${product.price}"
            tvSellerInfo.text = "Sold by: ${product.sellerInfo}"
            tvStock.text = "Stock: ${product.stock}"

            if(product.preOrder) tvPreOrder.text = "Pre-Order: Available"
            else tvPreOrder.text = "Pre-Order: Unavailable"

            if(product.deliveryInfo) tvDeliveryInfo.text = "FREE Delivery available for purchase over 35 Dollars"
            else tvDeliveryInfo.text = "FREE Delivery unavailable"

            GlideApp.with(this@DetailActivity)
                .load(product.image)
                .override(380, 230)
                .into(ivImage)

        }
    }

    override fun getPresenterView(): DetailContract.Presenter = presenter

    override fun init() {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .build().injectDetailScreen(this)
    }
}
