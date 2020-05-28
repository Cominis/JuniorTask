package com.dmt.juniortask.repository

import com.dmt.juniortask.storage.Storage
import javax.inject.Inject

private const val TOKEN = "token"

class UserManager @Inject constructor(private val storage: Storage) {

    val token: String
        get() = storage.getString(TOKEN)

    fun isUserLoggedIn() = storage.getString(TOKEN).isBlank()

    fun loginUser(token: String) {
        storage.setString(TOKEN, token)
    }

    fun logout() {
        storage.setString(TOKEN, "")
    }

}