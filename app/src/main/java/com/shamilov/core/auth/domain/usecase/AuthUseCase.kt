package com.shamilov.core.auth.domain.usecase

import com.shamilov.core.auth.domain.repository.AuthRepository

interface AuthUseCase {
    val isAuthorize: Boolean
    suspend fun createUser(): Result<Unit>
    suspend fun sendPhone(phone: String): Result<Unit>
    suspend fun sendCode(code: String): Result<Unit>
}

class AuthUseCaseImpl(
    private val authRepository: AuthRepository,
) : AuthUseCase {

    override val isAuthorize: Boolean
        get() = authRepository.isAuthorize

    override suspend fun createUser(): Result<Unit> {
        return authRepository.createUser()
    }

    override suspend fun sendPhone(phone: String): Result<Unit> {
        return authRepository.sendPhone(phone)
    }

    override suspend fun sendCode(code: String): Result<Unit> {
        return authRepository.sendCode(code)
    }
}