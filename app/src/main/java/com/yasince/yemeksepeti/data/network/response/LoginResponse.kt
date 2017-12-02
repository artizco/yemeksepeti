package com.yasince.yemeksepeti.data.network.response

import com.yasince.yemeksepeti.data.network.model.Login

class LoginResponse {
    var login: Login? = null

    constructor(login: Login) {
        this.login = login
    }
}