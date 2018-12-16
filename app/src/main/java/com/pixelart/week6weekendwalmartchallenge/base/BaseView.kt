package com.pixelart.week6weekendwalmartchallenge.base

import android.widget.ProgressBar

interface BaseView {

    fun showMessage(message: String)
    fun showError(error: String)
    fun showLoadingIndicator(progressBar: ProgressBar)
    fun hideLoadingIndicator(progressBar: ProgressBar)
}