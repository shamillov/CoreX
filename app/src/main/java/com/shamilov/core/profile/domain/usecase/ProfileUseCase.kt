package com.shamilov.core.profile.domain.usecase

import com.shamilov.core.profile.domain.model.User
import com.shamilov.core.profile.domain.repository.ProfileRepository
import javax.inject.Inject

interface ProfileUseCase {
    suspend fun getUserProfile(): Result<User>
}

class ProfileUseCaseImpl @Inject constructor(
    private val repository: ProfileRepository,
) : ProfileUseCase {
    override suspend fun getUserProfile(): Result<User> {
        return repository.getUserProfile()
    }
}