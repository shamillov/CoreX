package com.shamilov.core

import android.app.Application
import com.shamilov.core.common.di.DaggerComponent
import com.shamilov.core.di.AppComponent
import com.shamilov.core.di.DaggerAppComponent

class App : Application(), DaggerComponent {

    override lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(applicationContext)
    }
}