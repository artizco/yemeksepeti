package com.yasince.yemeksepeti.ui.profile

import com.yasince.yemeksepeti.data.DataManager
import com.yasince.yemeksepeti.data.network.response.UserProfileResponse
import com.yasince.yemeksepeti.ui.base.BasePresenter
import com.yasince.yemeksepeti.ui.listeners.UserProfileListener

class ProfilePresenter<V : ProfileMvpView>(dataManager: DataManager) : BasePresenter<V>(dataManager), ProfileMvpPresenter<V> {

    override fun onAttach(mvpView: V) {
        this.mvpView = mvpView
    }

    override fun getUserProfile(userId: String) {
        if (userId == null || userId.isEmpty()) {
            mvpView?.showUserIdError()
        }
        dataManager.getUserProfile(userId, userProfileListener)
    }

    val userProfileListener = object : UserProfileListener {
        override fun onResponse(response: UserProfileResponse) {
            if (response.profile == null) {
                mvpView?.showUsersError("")
            }
            mvpView?.loadProfile(response.profile!!)
        }

        override fun onError(errorMessage: String) {
            mvpView?.showUsersError(errorMessage)
        }
    }
}