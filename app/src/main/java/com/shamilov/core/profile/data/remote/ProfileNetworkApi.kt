package com.shamilov.core.profile.data.remote

import com.shamilov.core.common.models.Data
import com.shamilov.core.profile.data.model.UserResponse
import retrofit2.http.GET

interface ProfileNetworkApi {
    @GET("auth/profile")
    suspend fun getUserProfile(): Data<UserResponse>
}