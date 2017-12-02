package com.yasince.yemeksepeti.ui.login

import com.yasince.yemeksepeti.data.DataManager
import com.yasince.yemeksepeti.data.network.response.LoginResponse
import com.yasince.yemeksepeti.ui.base.BasePresenter
import com.yasince.yemeksepeti.ui.listeners.LoginListener

class LoginPresenter<V : LoginMvpView>(dataManager: DataManager) : BasePresenter<V>(dataManager),
        LoginMvpPresenter<V> {

    private val MIN_USERNAME_LENGHT = 3
    private val MIN_PASSWORD_LENGHT = 4

    override fun onAttach(mvpView: V) {
        this.mvpView = mvpView
    }

    override fun login(username: String, password: String) {
        if (!validateUsername(username)) {
            mvpView?.showUsernameError()
            return
        }

        if (!validatePassword(password)) {
            mvpView?.showPasswordError()
            return
        }

        dataManager.login(username, password, loginListener)
    }

    private val loginListener = object : LoginListener {
        override fun onResponse(response: LoginResponse) {
            val token: String? = response.login?.token
            if (token == null || token.isEmpty()) {
                mvpView?.showLoginError("")
                return
            }
            mvpView?.openUsersPage()
            dataManager.saveToken(token)
        }

        override fun onError(errorMessage: String) {
            mvpView?.showLoginError(errorMessage)
        }
    }

    override fun validateUsername(username: String): Boolean {
        return username.length > MIN_USERNAME_LENGHT
    }

    override fun validatePassword(password: String): Boolean {
        return password.length > MIN_PASSWORD_LENGHT
    }
}
