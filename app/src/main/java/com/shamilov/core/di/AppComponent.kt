package com.shamilov.core.di

import android.content.Context
import com.shamilov.core.auth.di.AuthModule
import com.shamilov.core.common.di.NetworkModule
import com.shamilov.core.common.di.ViewModelFactory
import com.shamilov.core.common.di.ViewModelModule
import com.shamilov.core.components.di.ComponentModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        ComponentModule::class,
        AuthModule::class,
        ViewModelModule::class,
    ]
)
interface AppComponent {
    val viewModelFactory: ViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            context: Context,
        ): AppComponent
    }
}