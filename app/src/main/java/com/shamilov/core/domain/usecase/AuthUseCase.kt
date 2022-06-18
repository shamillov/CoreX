package com.shamilov.core.domain.usecase

import com.shamilov.core.domain.repository.AuthRepository

class AuthUseCase(
    private val repository: AuthRepository,
) {

    suspend fun sendPhone(phone: String): Result<Unit> {
        return repository.sendPhone(phone).fold(
            { Result.success(Unit) },
            { Result.failure(it) }
        )
    }

    suspend fun sendCode(code: String) {

    }

}