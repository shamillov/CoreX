package com.shamilov.core.auth.domain.usecase

import com.shamilov.core.auth.domain.repository.AuthRepository
import javax.inject.Inject

interface AuthUseCase {
    val isAuthorize: Boolean
    val isFullUser: Boolean
    suspend fun createUser(): Result<Unit>
    suspend fun sendPhone(phone: String): Result<Unit>
    suspend fun sendCode(code: String): Result<Unit>
    suspend fun logout(): Result<Unit>
}

class AuthUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
) : AuthUseCase {

    override val isAuthorize: Boolean
        get() = authRepository.isAuthorize
    override val isFullUser: Boolean
        get() = authRepository.isFullUser

    override suspend fun createUser(): Result<Unit> {
        return authRepository.createUser()
    }

    override suspend fun sendPhone(phone: String): Result<Unit> {
        return authRepository.sendPhone(phone)
    }

    override suspend fun sendCode(code: String): Result<Unit> {
        return authRepository.sendCode(code)
    }

    override suspend fun logout(): Result<Unit> {
        return authRepository.logout()
    }
}