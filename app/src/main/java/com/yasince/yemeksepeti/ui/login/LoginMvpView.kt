package com.yasince.yemeksepeti.ui.login

import com.yasince.yemeksepeti.ui.base.MvpView

interface LoginMvpView : MvpView {

    fun initPresenter()

    fun initUI()

    fun onLoginClick()

    fun openUsersPage()

    fun showUsernameError()

    fun showPasswordError()

    fun showLoginError(error: String)
}