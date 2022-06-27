package com.shamilov.core.auth.di

import com.shamilov.core.auth.data.local.AuthPreferences
import com.shamilov.core.auth.data.local.AuthPreferencesImpl
import com.shamilov.core.auth.data.remote.AuthNetworkApi
import com.shamilov.core.auth.data.repository.AuthRepositoryImpl
import com.shamilov.core.auth.domain.repository.AuthRepository
import com.shamilov.core.auth.domain.usecase.AuthUseCase
import com.shamilov.core.auth.domain.usecase.AuthUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
interface AuthModule {
    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun bindAuthUseCase(impl: AuthUseCaseImpl): AuthUseCase

    @Binds
    fun bindAuthPreferences(impl: AuthPreferencesImpl): AuthPreferences

    companion object {
        @Provides
        fun provideAuthNetworkApi(retrofit: Retrofit): AuthNetworkApi =
            retrofit.create(AuthNetworkApi::class.java)
    }
}