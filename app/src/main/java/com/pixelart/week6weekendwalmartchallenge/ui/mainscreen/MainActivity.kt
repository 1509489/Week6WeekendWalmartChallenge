package com.pixelart.week6weekendwalmartchallenge.ui.mainscreen

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pixelart.week6weekendwalmartchallenge.R
import com.pixelart.week6weekendwalmartchallenge.adapter.ProductsAdapter
import com.pixelart.week6weekendwalmartchallenge.base.BaseActivity
import com.pixelart.week6weekendwalmartchallenge.databinding.ActivityMainBinding
import com.pixelart.week6weekendwalmartchallenge.di.ApplicationModule
import com.pixelart.week6weekendwalmartchallenge.di.DaggerApplicationComponent
import com.pixelart.week6weekendwalmartchallenge.di.NetworkModule
import com.pixelart.week6weekendwalmartchallenge.model.ApiResponse
import com.pixelart.week6weekendwalmartchallenge.model.Item
import com.pixelart.week6weekendwalmartchallenge.model.Product
import com.pixelart.week6weekendwalmartchallenge.ui.detailscreen.DetailActivity
import javax.inject.Inject

class MainActivity: BaseActivity<MainContract.Presenter> (), MainContract.View, ProductsAdapter.OnItemClickedListener {

    private val TAG = "MainActivity"

    @Inject
    lateinit var presenter: MainContract.Presenter
    @Inject
    lateinit var mainBinder: ActivityMainBinding

    private lateinit var adapter: ProductsAdapter
    private lateinit var products: ArrayList<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showLoadingIndicator(mainBinder.progressBar)
        presenter.getProducts()

        products = ArrayList()
    }

    override fun showProducts(apiResponse: ApiResponse) {
        products = apiResponse.items as ArrayList<Item>
        val layoutAnim: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_anim_fall_down)
        adapter = ProductsAdapter(this)
        mainBinder.rvProducts.layoutManager = LinearLayoutManager(this)
        mainBinder.rvProducts.adapter = adapter
        mainBinder.rvProducts.layoutAnimation = layoutAnim
        adapter.submitList(apiResponse.items)

        if (presenter.dataLoaded())
        {
            hideLoadingIndicator(mainBinder.progressBar)
        }

        adapter

    }

    override fun getPresenterView(): MainContract.Presenter = presenter

    override fun init() {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .build().injectMainScreen(this)
    }

    override fun onItemClicked(position: Int) {

        val product = Product(
            products[position].name,
            products[position].salePrice,
            products[position].stock,
            products[position].shortDescription,
            products[position].longDescription,
            products[position].sellerInfo,
            products[position].freeShippingOver35Dollars,
            products[position].preOrder,
            products[position].thumbnailImage
        )

        startActivity(Intent(this, DetailActivity::class.java).also {
            it.putExtra("product", product)
        })
    }
}
