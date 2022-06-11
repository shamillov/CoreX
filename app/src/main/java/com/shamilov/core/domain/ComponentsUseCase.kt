package com.shamilov.core.domain

import com.shamilov.core.domain.model.Component
import com.shamilov.core.domain.repository.ComponentsRepository

class ComponentsUseCase(
    private val repository: ComponentsRepository
) {
    suspend fun getComponents(): Result<List<Component>> {
        return repository.getComponents()
    }
}