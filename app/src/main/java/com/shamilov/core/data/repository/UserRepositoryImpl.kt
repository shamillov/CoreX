package com.shamilov.core.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.shamilov.core.domain.repository.UserRepository
import java.util.*

class UserRepositoryImpl(context: Context) : UserRepository {

    companion object {
        private const val PREFERENCES_USER = "PREFERENCES_USER"
        private const val KEY_TOKEN = "KEY_TOKEN"
        private const val KEY_UUID = "KEY_UUID"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(
            PREFERENCES_USER,
            Context.MODE_PRIVATE
        )

    override val isAuthorize: Boolean
        get() = getToken != null

    override val getToken: String?
        get() = sharedPreferences.getString(KEY_TOKEN, null)

    override fun saveToken(token: String) {
        sharedPreferences.edit()
            .putString(KEY_TOKEN, token)
            .apply()
    }

    override fun removeToken(token: String) {
        sharedPreferences.edit()
            .remove(KEY_TOKEN)
            .apply()
    }

    override fun getUUID(): String {
        return sharedPreferences.getString(KEY_UUID, null) ?: createAndSaveUUID()
    }

    private fun createAndSaveUUID(): String {
        val uuid = UUID.randomUUID().toString()
        sharedPreferences.edit()
            .putString(KEY_UUID, uuid)
            .apply()

        return uuid
    }
}