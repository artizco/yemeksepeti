package com.yasince.yemeksepeti.data.network

import com.yasince.yemeksepeti.data.network.request.LoginRequest
import com.yasince.yemeksepeti.data.network.response.LoginResponse
import com.yasince.yemeksepeti.data.network.response.UserProfileResponse
import com.yasince.yemeksepeti.data.network.response.UsersResponse

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WebService {

    @get:GET("users")
    val users: Call<UsersResponse>

    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @GET("profile/{userId}")
    fun getUserProfile(@Query("userId") userId: String): Call<UserProfileResponse>
}
