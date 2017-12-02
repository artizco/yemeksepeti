package com.yasince.yemeksepeti.ui.base


import com.yasince.yemeksepeti.data.DataManager

open class BasePresenter<V : MvpView>(val dataManager: DataManager) : MvpPresenter<V> {

    var mvpView: V? = null

    override fun onAttach(mvpView: V) {
        this.mvpView = mvpView
    }
}
