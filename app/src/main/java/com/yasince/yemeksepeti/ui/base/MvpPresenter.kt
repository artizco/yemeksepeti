package com.yasince.yemeksepeti.ui.base

interface MvpPresenter<in V : MvpView> {
    fun onAttach(mvpView: V)
}
