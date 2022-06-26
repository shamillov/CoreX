package com.shamilov.core.presentation.main.viewmodel

sealed class MainState {
    object Loading : MainState()
    class Loaded(val components: List<Any>) : MainState()
    class Error(val message: String) : MainState()
}