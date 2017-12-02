package com.yasince.yemeksepeti.ui.listeners


import com.yasince.yemeksepeti.data.network.response.LoginResponse

interface LoginListener : ErrorListener {
    fun onResponse(response: LoginResponse)
}
