package com.shamilov.core.data.repository

import com.shamilov.core.data.model.AuthResponse
import com.shamilov.core.data.model.requests.AuthRequest
import com.shamilov.core.data.network.CoreApi
import com.shamilov.core.domain.repository.AuthRepository
import kotlinx.coroutines.delay

class AuthRepositoryImpl(
    private val api: CoreApi,
) : AuthRepository {

    override suspend fun sendPhone(phone: String): Result<AuthResponse> {
        //send request
        delay(1000)
        return Result.success(AuthResponse("","", 0))
    }

    override suspend fun sendConde() {
        TODO("Not yet implemented")
    }

}