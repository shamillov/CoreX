package com.shamilov.core.presentation.main.viewmodel

import com.shamilov.core.components.domain.model.Component

sealed class MainState {
    object Loading : MainState()
    class Loaded(val components: List<Component>) : MainState()
    class Error(val message: String) : MainState()
}