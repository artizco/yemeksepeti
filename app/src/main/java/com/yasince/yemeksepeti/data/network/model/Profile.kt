package com.yasince.yemeksepeti.data.network.model

open class Profile {
    val id: Long? = 0
    val name: String? = ""
    val lastName: String? = ""
    val pic: String? = ""
    val address: String? = ""
    val phone: String? = ""
    val birthDate: String? = ""

    fun fullName(): String {
        return StringBuilder().append(name).append(" ").append(lastName).toString()
    }
}
