package com.shamilov.core.auth.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

object HttpClient {
    @Volatile
    private var retrofit: Retrofit? = null

    fun createHttpClient(token: String?): Retrofit {
        if (retrofit == null) {
            synchronized(this) {
                if (retrofit == null) {
                    val headerInterceptor = Interceptor { chain ->
                        val requestBuilder = chain.request()
                            .newBuilder()
                            .addHeader("Authorization", "Bearer $token")

                        chain.proceed(requestBuilder.build())
                    }

                    val okHttpClient = OkHttpClient.Builder()
                        .addInterceptor(headerInterceptor)
                        .build()

                    val json = Json {
                        ignoreUnknownKeys = true
                    }

                    retrofit = Retrofit.Builder()
                        .baseUrl("http://192.168.1.230:8035/api/v1/")
                        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                        .client(okHttpClient)
                        .build()
                }
            }
        }

        return retrofit!!
    }
}