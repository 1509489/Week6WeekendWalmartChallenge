package com.pixelart.week6weekendwalmartchallenge.base

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<T: BasePresenter>: AppCompatActivity(), BaseView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun showLoadingIndicator(progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoadingIndicator(progressBar: ProgressBar) {
        progressBar.visibility = View.INVISIBLE
    }

    abstract fun getPresenterView():T
    abstract fun init()
}