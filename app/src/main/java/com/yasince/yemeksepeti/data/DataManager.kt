package com.yasince.yemeksepeti.data


import com.yasince.yemeksepeti.ui.listeners.LoginListener
import com.yasince.yemeksepeti.ui.listeners.UserProfileListener
import com.yasince.yemeksepeti.ui.listeners.UsersListener

interface DataManager {

    fun getUsers(listener: UsersListener)

    fun login(username: String, password: String, listener: LoginListener)

    fun getUserProfile(userId: String, listener: UserProfileListener)

    fun saveToken(token: String)
}
