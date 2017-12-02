package com.yasince.yemeksepeti.ui.users

import com.yasince.yemeksepeti.data.DataManager
import com.yasince.yemeksepeti.data.network.response.UsersResponse
import com.yasince.yemeksepeti.ui.base.BasePresenter
import com.yasince.yemeksepeti.ui.listeners.UsersListener

class UsersPresenter<V : UsersMvpView>(dataManager: DataManager) : BasePresenter<V>(dataManager), UsersMvpPresenter<V> {

    override fun onAttach(mvpView: V) {
        super.onAttach(mvpView)
        this.mvpView = mvpView
    }

    override fun getUsers() {
        dataManager.getUsers(userListener)
    }

    val userListener = object : UsersListener {
        override fun onError(errorMessage: String) {
            mvpView?.showUsersError(errorMessage)
        }

        override fun onResponse(response: UsersResponse) {
            if (response.users == null || response.users.isEmpty()) {
                mvpView?.noUsersFound()
                return
            }
            mvpView?.loadUsers(response.users)
        }
    }
}