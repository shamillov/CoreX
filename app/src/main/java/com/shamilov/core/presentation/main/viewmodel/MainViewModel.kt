package com.shamilov.core.presentation.main.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.shamilov.core.components.domain.usecase.ComponentsUseCase
import com.shamilov.core.di.AppComponent
import com.shamilov.core.presentation.ComponentsViewDataMapper
import kotlinx.coroutines.launch

class MainViewModel(
    private val componentsUseCase: ComponentsUseCase,
    private val mapper: ComponentsViewDataMapper,
) : ViewModel() {

    private var _state = mutableStateOf<MainState>(MainState.Loading)
    val state: State<MainState> = _state

    init {
        loadComponents()
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
}

class MainViewModeFactory(private val appComponent: AppComponent) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(appComponent.getComponentUseCase(), appComponent.getComponentMapper()) as T
    }
}