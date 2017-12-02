package com.yasince.yemeksepeti.ui.users

import com.yasince.yemeksepeti.ui.base.MvpPresenter
import com.yasince.yemeksepeti.ui.base.MvpView

interface UsersMvpPresenter<in V : MvpView> : MvpPresenter<V>{

    fun getUsers()
}
