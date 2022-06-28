package com.shamilov.core.cart.di

import com.shamilov.core.cart.data.remote.CartNetworkApi
import com.shamilov.core.cart.data.repository.CartRepositoryImpl
import com.shamilov.core.cart.domain.repository.CartRepository
import com.shamilov.core.cart.domain.usecase.CartUseCase
import com.shamilov.core.cart.domain.usecase.CartUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
interface CartModule {

    @Binds
    @Singleton
    fun bindCartRepository(impl: CartRepositoryImpl): CartRepository

    @Binds
    @Singleton
    fun bindCartUseCase(impl: CartUseCaseImpl): CartUseCase

    companion object {
        @Provides
        @Singleton
        fun provideCartNetworkApi(retrofit: Retrofit): CartNetworkApi =
            retrofit.create(CartNetworkApi::class.java)
    }
}