package com.shamilov.core.components.presentation.viewmodel

sealed class ComponentsState {
    object Loading : ComponentsState()
    class Loaded(val components: List<Any>) : ComponentsState()
    class Error(val message: String) : ComponentsState()
}