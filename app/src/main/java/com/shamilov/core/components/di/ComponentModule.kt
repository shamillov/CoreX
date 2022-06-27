package com.shamilov.core.components.di

import com.shamilov.core.components.data.remote.ComponentsNetworkApi
import com.shamilov.core.components.data.repository.ComponentsRepositoryImpl
import com.shamilov.core.components.domain.repository.ComponentsRepository
import com.shamilov.core.components.domain.usecase.ComponentsUseCase
import com.shamilov.core.components.domain.usecase.ComponentsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
interface ComponentModule {

    @Binds
    fun bindComponentRepository(impl: ComponentsRepositoryImpl): ComponentsRepository

    @Binds
    fun bindComponentUseCase(impl: ComponentsUseCaseImpl): ComponentsUseCase

    companion object {
        @Provides
        fun provideComponentNetworkApi(
            retrofit: Retrofit,
        ): ComponentsNetworkApi = retrofit.create(ComponentsNetworkApi::class.java)
    }
}