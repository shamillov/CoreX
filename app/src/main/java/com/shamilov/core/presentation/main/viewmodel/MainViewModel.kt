package com.shamilov.core.presentation.main.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.shamilov.core.auth.data.remote.HttpClient
import com.shamilov.core.components.data.mapper.*
import com.shamilov.core.components.data.remote.ComponentsNetworkApi
import com.shamilov.core.components.data.repository.ComponentsRepositoryImpl
import com.shamilov.core.components.domain.repository.ComponentsRepository
import com.shamilov.core.components.domain.usecase.ComponentsUseCase
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

class MainViewModeFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val httpClient = HttpClient.createHttpClient(null)
        val componentsApi = httpClient.create(ComponentsNetworkApi::class.java)
        val bannerComponentMapper = BannerComponentMapper()
        val componentsRepository: ComponentsRepository = ComponentsRepositoryImpl(
            componentsApi,
            ComponentsMapper(
                HeaderComponentMapper(),
                bannerComponentMapper,
                BannersComponentMapper(),
                CardsComponentResponseMapper(),
            ),
        )
        val componentsUseCase = ComponentsUseCase(componentsRepository)
        val mapper = ComponentsViewDataMapper()
        return MainViewModel(componentsUseCase, mapper) as T
    }

}