package com.shamilov.core.common.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.shamilov.core.auth.data.local.AuthPreferences
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
class NetworkModule {
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        json: Json,
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://192.168.1.230:8035/api/v1/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    fun provideOkHttpClient(
        tokenInterceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
            .build()
    }

    @Provides
    fun provideTokenInterceptor(
        authPreferences: AuthPreferences,
    ): Interceptor {
        return Interceptor { chain ->
            val requestBuilder = chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer ${authPreferences.getToken()}")
                .build()

            chain.proceed(requestBuilder)
        }
    }

    @Provides
    fun provideJsonSerializer() = Json { ignoreUnknownKeys = true }
}