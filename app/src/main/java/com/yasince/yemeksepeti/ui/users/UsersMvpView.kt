package com.yasince.yemeksepeti.ui.users

import com.yasince.yemeksepeti.data.network.model.User
import com.yasince.yemeksepeti.ui.base.MvpView

interface UsersMvpView : MvpView {

    fun initPresenter()

    fun initUI()

    fun loadUsers(users: ArrayList<User>)

    fun showUsersError(error: String)

    fun noUsersFound()

    fun openUserProfilePage(userId: String)
}