package com.yasince.yemeksepeti.ui.login

import com.yasince.yemeksepeti.ui.base.MvpPresenter
import com.yasince.yemeksepeti.ui.base.MvpView

interface LoginMvpPresenter<in V : MvpView> : MvpPresenter<V> {

    fun login(username: String, password: String)

    fun validateUsername(username: String): Boolean

    fun validatePassword(password: String): Boolean
}
