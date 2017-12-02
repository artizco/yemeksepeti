package com.yasince.yemeksepeti.data

import android.content.Context
import android.text.TextUtils
import com.yasince.yemeksepeti.data.db.DbHelper
import com.yasince.yemeksepeti.data.network.ApiHelper
import com.yasince.yemeksepeti.data.network.model.Login
import com.yasince.yemeksepeti.data.network.request.LoginRequest
import com.yasince.yemeksepeti.data.network.response.LoginResponse
import com.yasince.yemeksepeti.data.network.response.UserProfileResponse
import com.yasince.yemeksepeti.data.network.response.UsersResponse
import com.yasince.yemeksepeti.data.prefs.PreferencesHelper
import com.yasince.yemeksepeti.ui.listeners.LoginListener
import com.yasince.yemeksepeti.ui.listeners.UserProfileListener
import com.yasince.yemeksepeti.ui.listeners.UsersListener
import com.yasince.yemeksepeti.utils.AppConstants
import com.yasince.yemeksepeti.utils.CommonUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppDataManager(private val context: Context,
                     private val prefHelper: PreferencesHelper,
                     private val apiHelper: ApiHelper,
                     private val dbHelper: DbHelper) : DataManager {

    private val isMock = true

    override fun login(username: String, password: String, listener: LoginListener) {
        if (isMock) {
            listener.onResponse(mockLoginResponse())
        } else {
            var token: String? = prefHelper.getToken()
            if (!TextUtils.isEmpty(token)) {
                listener.onResponse(LoginResponse(Login(token!!)))
            } else {
                apiHelper.login(LoginRequest(username, password), object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>,
                                            response: Response<LoginResponse>) {
                        token = response.body()?.login?.token
                        if (TextUtils.isEmpty(token)) {
                            listener.onError("")
                            return
                        }

                        listener.onResponse(response.body()!!)
                        saveToken(token!!)
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        listener.onError(t.message.toString())
                    }
                })
            }
        }
    }

    override fun getUsers(listener: UsersListener) {
        if (isMock) {
            listener.onResponse(mockUsersResponse())
        } else {
            apiHelper.getUsers(object : Callback<UsersResponse> {
                override fun onResponse(call: Call<UsersResponse>,
                                        response: Response<UsersResponse>) {

                    if (response.body() == null) {
                        listener.onError("")
                        return
                    }

                    listener.onResponse(response.body()!!)
                }

                override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                    listener.onError(t.message.toString())
                }
            })
        }
    }

    override fun getUserProfile(userId: String, listener: UserProfileListener) {
        if (isMock) {
            listener.onResponse(mockProfile())
        } else {
            apiHelper.getUserProfile(userId, object : Callback<UserProfileResponse> {
                override fun onResponse(call: Call<UserProfileResponse>,
                                        response: Response<UserProfileResponse>) {

                    if (response.body() == null) {
                        listener.onError("")
                        return
                    }
                    listener.onResponse(response.body()!!)
                }

                override fun onFailure(call: Call<UserProfileResponse>, t: Throwable) {
                    listener.onError(t.message.toString())
                }
            })
        }
    }

    override fun saveToken(token: String) {
        prefHelper.setToken(token)
    }

    private fun mockLoginResponse(): LoginResponse {
        return CommonUtils.mockIt(context, AppConstants.MOCK_LOGIN, LoginResponse::class.java)
    }

    private fun mockUsersResponse(): UsersResponse {
        return CommonUtils.mockIt(context, AppConstants.MOCK_USERS, UsersResponse::class.java)
    }

    private fun mockProfile(): UserProfileResponse {
        return CommonUtils.mockIt(context, AppConstants.MOCK_PROFILE, UserProfileResponse::class.java)
    }
}
