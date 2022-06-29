package com.shamilov.core.components.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shamilov.core.auth.domain.usecase.AuthUseCase
import com.shamilov.core.components.domain.usecase.ComponentsUseCase
import com.shamilov.core.components.presentation.mapper.ComponentsViewDataMapper
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ComponentsViewModel @Inject constructor(
    private val componentsUseCase: ComponentsUseCase,
    private val mapper: ComponentsViewDataMapper,
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    private var _state = mutableStateOf<ComponentsState>(ComponentsState.Loading)
    val state: State<ComponentsState> = _state

    private var _effect = MutableSharedFlow<ComponentsEffect>()
    val effect: SharedFlow<ComponentsEffect> = _effect.asSharedFlow()

    init {
        if (authUseCase.isAuthorize) {
            loadComponents()
        } else {
            createUser()
        }
    }

    fun accept(message: ComponentsMessage) = when (message) {
        is ComponentsMessage.OnProfileClicked -> onProfileClicked()
    }

    private fun loadComponents() {
        viewModelScope.launch {
            componentsUseCase.getComponents()
                .onSuccess {
                    val components = mapper.mapComponentsView(it)
                    _state.value = ComponentsState.Loaded(components)
                }
                .onFailure {
                    _state.value = ComponentsState.Error(it.message.toString())
                }
        }
    }

    private fun createUser() {
        viewModelScope.launch {
            authUseCase.createUser()
                .onSuccess { loadComponents() }
                .onFailure { _state.value = ComponentsState.Error(it.message.orEmpty()) }
        }
    }

    private fun onProfileClicked() {
        viewModelScope.launch {
            val screen = when (authUseCase.isFullUser) {
                true -> ComponentsEffect.OpenUserProfileScreen
                else -> ComponentsEffect.OpenAuthScreen
            }
            _effect.emit(screen)
        }
    }
}