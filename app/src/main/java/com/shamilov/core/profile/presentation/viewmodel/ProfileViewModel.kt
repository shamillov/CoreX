package com.shamilov.core.profile.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shamilov.core.auth.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
): ViewModel() {

    private var _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

    private var _effect = MutableSharedFlow<ProfileEffect>()
    val effect: SharedFlow<ProfileEffect> = _effect.asSharedFlow()

    fun accept(message: ProfileMessage) = when (message) {
        is ProfileMessage.OnLogoutClicked -> logout()
    }

    private fun logout() {
        _state.value = state.value.copy(isEnabled = false, isLoading = true)

        viewModelScope.launch {
            authUseCase.logout()
                .onSuccess {
                    _effect.emit(ProfileEffect.OpenAuthScreen)
                }
                .onFailure {
                    _state.value = state.value.copy(isEnabled = true, isLoading = false)
                    _effect.emit(ProfileEffect.ShowMessage(it.message.orEmpty()))
                }
        }
    }
}