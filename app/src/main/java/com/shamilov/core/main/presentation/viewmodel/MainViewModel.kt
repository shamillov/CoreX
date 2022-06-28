package com.shamilov.core.main.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shamilov.core.auth.domain.usecase.AuthUseCase
import com.shamilov.core.components.domain.usecase.ComponentsUseCase
import com.shamilov.core.components.presentation.mapper.ComponentsViewDataMapper
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val componentsUseCase: ComponentsUseCase,
    private val mapper: ComponentsViewDataMapper,
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    private var _state = mutableStateOf<MainState>(MainState.Loading)
    val state: State<MainState> = _state

    init {
        if (authUseCase.isAuthorize) {
            loadComponents()
        } else {
            createUser()
        }
    }

    private fun loadComponents() {
        viewModelScope.launch {
            componentsUseCase.getComponents()
                .onSuccess {
                    val components = mapper.mapComponentsView(it)
                    _state.value = MainState.Loaded(components)
                }
                .onFailure {
                    _state.value = MainState.Error(it.message.toString())
                }
        }
    }

    private fun createUser() {
        viewModelScope.launch {
            authUseCase.createUser()
                .onSuccess { loadComponents() }
                .onFailure { _state.value = MainState.Error(it.message.orEmpty()) }
        }
    }
}