package com.yasince.yemeksepeti.data.network

import com.yasince.yemeksepeti.data.network.request.LoginRequest
import com.yasince.yemeksepeti.data.network.response.LoginResponse
import com.yasince.yemeksepeti.data.network.response.UserProfileResponse
import com.yasince.yemeksepeti.data.network.response.UsersResponse

import retrofit2.Callback

interface ApiHelper {

    fun login(loginRequest: LoginRequest, callback: Callback<LoginResponse>)

    fun getUsers(callback: Callback<UsersResponse>)

    fun getUserProfile(userId: String, callback: Callback<UserProfileResponse>)
}
