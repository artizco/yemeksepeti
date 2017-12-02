package com.yasince.yemeksepeti.data.network.model

class User {
    val id: Long? = 0
    val name: String? = ""
    val lastName: String? = ""
    val pic: String? = ""

    fun fullName(): String {
        return StringBuilder().append(name).append(" ").append(lastName).toString()
    }
}
