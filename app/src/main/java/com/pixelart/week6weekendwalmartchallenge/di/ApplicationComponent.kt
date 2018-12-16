package com.pixelart.week6weekendwalmartchallenge.di

import com.pixelart.week6weekendwalmartchallenge.ui.detailscreen.DetailActivity
import com.pixelart.week6weekendwalmartchallenge.ui.mainscreen.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun injectMainScreen(mainActivity: MainActivity)
    fun injectDetailScreen(detailActivity: DetailActivity)
}