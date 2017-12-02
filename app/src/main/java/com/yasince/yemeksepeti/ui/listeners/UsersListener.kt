package com.yasince.yemeksepeti.ui.listeners

import com.yasince.yemeksepeti.data.network.response.UsersResponse

interface UsersListener : ErrorListener {
    fun onResponse(response: UsersResponse)
}
