package com.shamilov.core.auth.di

import androidx.lifecycle.ViewModel
import com.shamilov.core.auth.data.local.AuthPreferences
import com.shamilov.core.auth.data.local.AuthPreferencesImpl
import com.shamilov.core.auth.data.remote.AuthNetworkApi
import com.shamilov.core.auth.data.repository.AuthRepositoryImpl
import com.shamilov.core.auth.domain.repository.AuthRepository
import com.shamilov.core.auth.domain.usecase.AuthUseCase
import com.shamilov.core.auth.domain.usecase.AuthUseCaseImpl
import com.shamilov.core.auth.presentation.auth.viewmodel.AuthViewModel
import com.shamilov.core.auth.presentation.verification.viewmodel.VerificationViewModel
import com.shamilov.core.common.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module
interface AuthModule {
    @Binds
    @Reusable
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Reusable
    fun bindAuthUseCase(impl: AuthUseCaseImpl): AuthUseCase

    @Binds
    @Reusable
    fun bindAuthPreferences(impl: AuthPreferencesImpl): AuthPreferences

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VerificationViewModel::class)
    fun bindVerificationViewModel(viewModel: VerificationViewModel): ViewModel

    companion object {
        @Provides
        @Reusable
        fun provideAuthNetworkApi(retrofit: Retrofit): AuthNetworkApi =
            retrofit.create(AuthNetworkApi::class.java)
    }
}