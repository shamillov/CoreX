package com.shamilov.core.components.presentation.viewmodel

sealed class ComponentsEffect {
    object OpenUserProfileScreen : ComponentsEffect()
    object OpenAuthScreen : ComponentsEffect()
}