package com.shamilov.core.common.di

import android.content.Context
import com.shamilov.core.di.AppComponent

interface DaggerComponent {
    val appComponent: AppComponent
}

val Context.daggerComponent: AppComponent
    get() = (applicationContext as DaggerComponent).appComponent
