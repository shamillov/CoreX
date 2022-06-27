package com.shamilov.core.components.domain.usecase

import com.shamilov.core.components.domain.model.Component
import com.shamilov.core.components.domain.repository.ComponentsRepository
import javax.inject.Inject

interface ComponentsUseCase {
    suspend fun getComponents(): Result<List<Component>>
}

class ComponentsUseCaseImpl @Inject constructor(
    private val repository: ComponentsRepository,
) : ComponentsUseCase {
    override suspend fun getComponents(): Result<List<Component>> {
        return repository.getComponents()
    }
}