package com.yasince.yemeksepeti.ui.listeners

import com.yasince.yemeksepeti.data.network.response.UserProfileResponse

interface UserProfileListener : ErrorListener{
    fun onResponse(response: UserProfileResponse)
}
