package com.shamilov.core.profile.di

import androidx.lifecycle.ViewModel
import com.shamilov.core.common.di.ViewModelKey
import com.shamilov.core.profile.data.remote.ProfileNetworkApi
import com.shamilov.core.profile.data.respository.ProfileRepositoryImpl
import com.shamilov.core.profile.domain.repository.ProfileRepository
import com.shamilov.core.profile.domain.usecase.ProfileUseCase
import com.shamilov.core.profile.domain.usecase.ProfileUseCaseImpl
import com.shamilov.core.profile.presentation.viewmodel.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module
interface ProfileModule {

    @Binds
    fun bindProfileRepository(impl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    fun bindProfileUseCase(impl: ProfileUseCaseImpl): ProfileUseCase

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

    companion object {
        @Provides
        fun provideProfileNetworkApi(retrofit: Retrofit): ProfileNetworkApi {
            return retrofit.create(ProfileNetworkApi::class.java)
        }
    }
}