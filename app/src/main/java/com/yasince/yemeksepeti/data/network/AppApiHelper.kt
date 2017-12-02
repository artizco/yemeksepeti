package com.yasince.yemeksepeti.data.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.yasince.yemeksepeti.data.network.request.LoginRequest
import com.yasince.yemeksepeti.data.network.response.LoginResponse
import com.yasince.yemeksepeti.data.network.response.UserProfileResponse
import com.yasince.yemeksepeti.data.network.response.UsersResponse
import okhttp3.OkHttpClient
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppApiHelper : ApiHelper {

    private val BASE_URL = "https:api.yemeksepeti.com/"

    private var retrofit: Retrofit? = null
    private var service: WebService? = null

    private val isServiceAvailable: Boolean
        get() = service != null

    init {
        if (retrofit == null) {

            val okHttpClient = OkHttpClient.Builder()
                    .addNetworkInterceptor(StethoInterceptor())
                    .build()


            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        if (service == null)
            service = retrofit!!.create(WebService::class.java)
    }

    override fun login(loginRequest: LoginRequest, callback: Callback<LoginResponse>) {
        if (isServiceAvailable) {
            val call = service!!.login(loginRequest)
            call.enqueue(callback)
        }
    }

    override fun getUsers(callback: Callback<UsersResponse>) {
        if (isServiceAvailable) {
            val call = service!!.users
            call.enqueue(callback)
        }
    }

    override fun getUserProfile(userId: String, callback: Callback<UserProfileResponse>) {
        if (isServiceAvailable) {
            val call = service!!.getUserProfile(userId)
            call.enqueue(callback)
        }
    }

}
