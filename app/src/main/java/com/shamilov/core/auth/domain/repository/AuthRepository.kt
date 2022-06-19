package com.shamilov.core.auth.domain.repository

interface AuthRepository {
    val isAuthorize: Boolean
    suspend fun createUser(): Result<Unit>
    suspend fun sendPhone(phone: String): Result<Unit>
    suspend fun sendCode(code: String): Result<Unit>
}