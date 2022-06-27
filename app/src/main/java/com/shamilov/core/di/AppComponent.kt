package com.shamilov.core.di

import android.content.Context
import com.shamilov.core.auth.di.AuthModule
import com.shamilov.core.auth.domain.usecase.AuthUseCase
import com.shamilov.core.common.di.NetworkModule
import com.shamilov.core.components.di.ComponentModule
import com.shamilov.core.components.domain.usecase.ComponentsUseCase
import com.shamilov.core.presentation.ComponentsViewDataMapper
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        ComponentModule::class,
        AuthModule::class
    ]
)
interface AppComponent {
    fun getComponentUseCase(): ComponentsUseCase
    fun authUseCase(): AuthUseCase
    fun getComponentMapper(): ComponentsViewDataMapper

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            context: Context,
        ): AppComponent
    }
}