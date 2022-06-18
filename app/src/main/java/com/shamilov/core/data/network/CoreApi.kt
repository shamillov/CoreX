package com.shamilov.core.data.network

import com.shamilov.core.data.model.AuthResponse
import com.shamilov.core.data.model.ComponentResponse
import com.shamilov.core.data.model.Response
import com.shamilov.core.data.model.requests.AuthRequest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

typealias Components = Result<Response<List<ComponentResponse>>>

interface CoreApi {
    suspend fun getComponents(): Components

    @POST("auth/attempt")
    suspend fun sendPhone(@Body request: AuthRequest): Result<Response<AuthResponse>>

    companion object {
        private var retrofit: Retrofit? = null

        fun create(): CoreApi {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("http://10.1.0.155:8035/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit!!.create(CoreApi::class.java)
        }
    }
}