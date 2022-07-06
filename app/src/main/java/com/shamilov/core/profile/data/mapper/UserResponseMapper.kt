package com.shamilov.core.profile.data.mapper

import com.shamilov.core.profile.data.model.UserResponse
import com.shamilov.core.profile.domain.model.User
import javax.inject.Inject

class UserResponseMapper @Inject constructor() {
    fun mapUserResponse(response: UserResponse): User {
        return User(
            name = response.name,
            phone = response.phone,
        )
    }
}