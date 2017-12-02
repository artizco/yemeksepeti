package com.yasince.yemeksepeti.ui.profile

import com.yasince.yemeksepeti.ui.base.MvpPresenter
import com.yasince.yemeksepeti.ui.base.MvpView

interface ProfileMvpPresenter<in V : MvpView> : MvpPresenter<V> {

    fun getUserProfile(userId: String)
}
