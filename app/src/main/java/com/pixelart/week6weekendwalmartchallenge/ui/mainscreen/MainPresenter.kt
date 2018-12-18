package com.pixelart.week6weekendwalmartchallenge.ui.mainscreen

import com.pixelart.week6weekendwalmartchallenge.model.ApiResponse
import com.pixelart.week6weekendwalmartchallenge.remote.NetworkService
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainPresenter @Inject constructor(private val view: MainContract.View, private val networkService: NetworkService):
    MainContract.Presenter {

    private var dataLoaded = false

    override fun getProducts() {
        networkService.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ApiResponse>{
                override fun onSuccess(t: ApiResponse) {
                    view.showProducts(t.items)
                }

                override fun onSubscribe(d: Disposable) {
                    view.showMessage("Fetching Data from server")
                    dataLoaded = true
                }

                override fun onError(e: Throwable) {
                    view.showError("Failed to fetch data from service")
                }

            })
    }

    override fun dataLoaded(): Boolean = dataLoaded

    override fun onResume() {

    }

    override fun onDestroy() {

    }
}