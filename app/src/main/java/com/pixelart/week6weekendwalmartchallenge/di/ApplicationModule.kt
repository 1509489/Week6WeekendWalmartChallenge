package com.pixelart.week6weekendwalmartchallenge.di

import android.app.Activity
import androidx.databinding.DataBindingUtil
import com.pixelart.week6weekendwalmartchallenge.R
import com.pixelart.week6weekendwalmartchallenge.base.BaseActivity
import com.pixelart.week6weekendwalmartchallenge.databinding.ActivityDetailBinding
import com.pixelart.week6weekendwalmartchallenge.databinding.ActivityMainBinding
import com.pixelart.week6weekendwalmartchallenge.remote.NetworkService
import com.pixelart.week6weekendwalmartchallenge.ui.detailscreen.DetailActivity
import com.pixelart.week6weekendwalmartchallenge.ui.detailscreen.DetailContract
import com.pixelart.week6weekendwalmartchallenge.ui.detailscreen.DetailPresenter
import com.pixelart.week6weekendwalmartchallenge.ui.mainscreen.MainActivity
import com.pixelart.week6weekendwalmartchallenge.ui.mainscreen.MainContract
import com.pixelart.week6weekendwalmartchallenge.ui.mainscreen.MainPresenter
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class ApplicationModule(private val baseActivity: BaseActivity<*>) {

    @Provides
    fun providesMainContractPresenter(networkService: NetworkService):MainContract.Presenter = MainPresenter(baseActivity as MainActivity, networkService)

    @Provides
    fun funProvidesMainScreenBinding():ActivityMainBinding = DataBindingUtil.setContentView(baseActivity as Activity, R.layout.activity_main)

    @Provides
    fun providesDetailContractPresenter():DetailContract.Presenter = DetailPresenter(baseActivity as DetailActivity)

    @Provides
    fun providesDetailScreenBinding():ActivityDetailBinding = DataBindingUtil.setContentView(baseActivity as Activity, R.layout.activity_detail)
}