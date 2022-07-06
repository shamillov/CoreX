package com.shamilov.core.profile.data.respository

import com.shamilov.core.profile.data.mapper.UserResponseMapper
import com.shamilov.core.profile.data.remote.ProfileNetworkApi
import com.shamilov.core.profile.domain.model.User
import com.shamilov.core.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val api: ProfileNetworkApi,
    private val mapper: UserResponseMapper,
) : ProfileRepository {
    override suspend fun getUserProfile(): Result<User> {
        return try {
            val response = api.getUserProfile()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                val result = mapper.mapUserResponse(body.data)
                Result.success(result)
            } else {
                error(response.message())
            }
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}