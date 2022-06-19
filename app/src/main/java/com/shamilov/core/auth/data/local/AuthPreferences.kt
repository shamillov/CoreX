package com.shamilov.core.auth.data.local

import android.content.Context
import java.util.*

interface AuthPreferences {
    fun getToken(): String?
    fun saveToken(token: String)
    fun removeToken()
    fun getUUID(): String
}

class AuthPreferencesImpl(
    context: Context,
) : AuthPreferences {

    companion object {
        private const val PREFERENCES_NAME = "auth"

        private const val KEY_TOKEN = "token"
        private const val KEY_UUID = "uuid"
    }

    private val sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    override fun getToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null)
    }

    override fun saveToken(token: String) {
        sharedPreferences.edit()
            .putString(KEY_TOKEN, token)
            .apply()
    }

    override fun removeToken() {
        sharedPreferences.edit()
            .remove(KEY_TOKEN)
            .apply()
    }

    override fun getUUID(): String {
        return sharedPreferences.getString(KEY_UUID, null) ?: createAndSaveUUID()
    }

    private fun createAndSaveUUID(): String {
        val uuid = UUID.randomUUID().toString()
        saveUUID(uuid)
        return uuid
    }

    private fun saveUUID(uuid: String) {
        sharedPreferences.edit()
            .putString(KEY_UUID, uuid)
            .apply()
    }
}