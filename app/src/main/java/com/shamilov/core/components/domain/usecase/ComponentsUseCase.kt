package com.shamilov.core.components.domain.usecase

import com.shamilov.core.components.domain.model.Component
import com.shamilov.core.components.domain.repository.ComponentsRepository

class ComponentsUseCase(
    private val repository: ComponentsRepository
) {
    suspend fun getComponents(): Result<List<Component>> {
        return repository.getComponents()
    }
}