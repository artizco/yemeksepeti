package com.yasince.yemeksepeti.ui.profile

import com.yasince.yemeksepeti.data.network.model.Profile
import com.yasince.yemeksepeti.ui.base.MvpView

interface ProfileMvpView : MvpView {

    fun initPresenter()

    fun initUI()

    fun getUserProfile(userId: String)

    fun onBackClick()

    fun loadProfile(profile: Profile)

    fun showUsersError(error: String)

    fun showUserIdError()
}
