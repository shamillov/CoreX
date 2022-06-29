package com.shamilov.core.profile.domain.usecase

import com.shamilov.core.profile.domain.repository.ProfileRepository
import javax.inject.Inject

interface ProfileUseCase

class ProfileUseCaseImpl @Inject constructor(
    private val repository: ProfileRepository,
) : ProfileUseCase