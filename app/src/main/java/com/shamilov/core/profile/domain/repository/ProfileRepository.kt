package com.shamilov.core.profile.domain.repository

import com.shamilov.core.profile.domain.model.User

interface ProfileRepository {
    suspend fun getUserProfile(): Result<User>
}