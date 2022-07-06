package com.shamilov.core.auth.data.repository

import android.util.Base64
import android.util.Log
import com.shamilov.core.auth.data.local.AuthPreferences
import com.shamilov.core.auth.data.model.requests.CodeRequest
import com.shamilov.core.auth.data.model.requests.PhoneRequest
import com.shamilov.core.auth.data.model.requests.UuidRequest
import com.shamilov.core.auth.data.remote.AuthNetworkApi
import com.shamilov.core.auth.domain.repository.AuthRepository
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthNetworkApi,
    private val authPrefs: AuthPreferences,
) : AuthRepository {

    private var verifyToken: String? = null

    override val isAuthorize: Boolean
        get() = authPrefs.getToken() != null

    override val isFullUser: Boolean
        get() = checkUserRule()

    override suspend fun createUser(): Result<Unit> {
        val uuid = authPrefs.getUUID()
        val request = UuidRequest(uuid = uuid)

        return try {
            val response = api.createUser(request)
            val body = response.body()

            if (response.isSuccessful && body != null) {
                val token = body.data.accessToken
                authPrefs.saveToken(token)

                Result.success(Unit)
            } else {
                error(response.message())
            }
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    override suspend fun sendPhone(phone: String): Result<Unit> {
        val request = PhoneRequest(phone = phone)

        return try {
            val response = api.sendPhone(request)
            val body = response.body()

            if (response.isSuccessful && body != null) {
                verifyToken = body.data.token

                //need to send code
                Log.d("auth.code", body.data.code)
                Result.success(Unit)
            } else {
                error(response.message())
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun sendCode(code: String): Result<Unit> {
        val request = CodeRequest(code = code, verifyToken ?: error("verify token not most be null"))

        return try {
            val response = api.sendCode(request)
            val body = response.body()

            if (response.isSuccessful && body != null) {
                val accessToken = body.data.accessToken
                authPrefs.saveToken(accessToken)

                Result.success(Unit)
            } else {
                error(response.message())
            }
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    override suspend fun logout(): Result<Unit> {
        return try {
            val response = api.logout()

            if (response.isSuccessful) {
                authPrefs.removeToken()
                authPrefs.removeUUID()

                createUser()
            } else {
                error(response.message())
            }
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    /**
     * Return true if user is registered with phone
     */
    private fun checkUserRule(): Boolean {
        val token = authPrefs.getToken() ?: error("Token not must be null")
        val tokenSegments = token.split(".")

        for (segment in 0..tokenSegments.size) {
            try {
                val userProperties = Base64.decode(tokenSegments[segment], 0).decodeToString()
                val jsonElement = Json.parseToJsonElement(userProperties).jsonObject["fc"] ?: continue
                val isFullUser = jsonElement.jsonPrimitive.content

                return isFullUser.toBoolean()
            } catch (e: Throwable) {
                continue
            }
        }
        return false
    }
}