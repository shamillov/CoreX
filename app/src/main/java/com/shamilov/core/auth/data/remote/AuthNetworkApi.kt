package com.shamilov.core.auth.data.remote

import com.shamilov.core.auth.data.model.requests.CodeRequest
import com.shamilov.core.auth.data.model.requests.PhoneRequest
import com.shamilov.core.auth.data.model.requests.UuidRequest
import com.shamilov.core.auth.data.model.responses.CodeResponse
import com.shamilov.core.auth.data.model.responses.TokenResponse
import com.shamilov.core.common.models.Data
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthNetworkApi {

    @POST("auth/uuid")
    suspend fun createUser(@Body request: UuidRequest): Data<TokenResponse>

    @POST("auth/attempt")
    suspend fun sendPhone(@Body request: PhoneRequest): Data<CodeResponse>

    @POST("auth/login")
    suspend fun sendCode(@Body request: CodeRequest): Data<TokenResponse>

}