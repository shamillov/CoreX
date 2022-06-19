package com.shamilov.core.auth.data.repository

import android.util.Log
import com.shamilov.core.auth.data.local.AuthPreferences
import com.shamilov.core.auth.data.model.requests.CodeRequest
import com.shamilov.core.auth.data.model.requests.PhoneRequest
import com.shamilov.core.auth.data.model.requests.UuidRequest
import com.shamilov.core.auth.data.remote.AuthNetworkApi
import com.shamilov.core.auth.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val api: AuthNetworkApi,
    private val prefs: AuthPreferences,
) : AuthRepository {

    private var verifyToken: String? = null

    override val isAuthorize: Boolean
        get() = prefs.getToken() != null

    override suspend fun createUser(): Result<Unit> {
        val uuid = prefs.getUUID()
        val request = UuidRequest(uuid = uuid)

        return try {
            val response = api.createUser(request)
            val body = response.body()

            if (response.isSuccessful && body != null) {
                val token = body.data.accessToken
                prefs.saveToken(token)

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
                prefs.saveToken(accessToken)

                Result.success(Unit)
            } else {
                error(response.message())
            }
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}