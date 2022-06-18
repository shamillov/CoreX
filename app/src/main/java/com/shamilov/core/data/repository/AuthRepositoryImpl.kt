package com.shamilov.core.data.repository

import com.shamilov.core.data.model.AuthResponse
import com.shamilov.core.data.model.requests.AuthRequest
import com.shamilov.core.domain.repository.AuthRepository

class AuthRepositoryImpl : AuthRepository {

    override suspend fun sendPhone(phone: String): Result<AuthResponse> {
        val authRequest = AuthRequest(phone = phone)
        //send api request and save token
        return Result.success(
            AuthResponse("1234", "token...", 0)
        )
    }

    override suspend fun sendConde() {
        TODO("Not yet implemented")
    }

}