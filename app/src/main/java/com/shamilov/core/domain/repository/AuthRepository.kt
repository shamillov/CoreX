package com.shamilov.core.domain.repository

import com.shamilov.core.data.model.AuthResponse

interface AuthRepository {
    suspend fun sendPhone(phone: String): Result<AuthResponse>
    suspend fun sendConde()
}