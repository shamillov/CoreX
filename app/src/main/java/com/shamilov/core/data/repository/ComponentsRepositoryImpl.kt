package com.shamilov.core.data.repository

import com.shamilov.core.data.mapper.ComponentsMapper
import com.shamilov.core.data.network.CoreApi
import com.shamilov.core.domain.model.Component
import com.shamilov.core.domain.repository.ComponentsRepository

class ComponentsRepositoryImpl(
    private val api: CoreApi,
    private val mapper: ComponentsMapper,
) : ComponentsRepository {
    override suspend fun getComponents(): Result<List<Component>> {
        return api.getComponents().mapCatching {
            mapper.mapComponents(it.data)
        }
    }
}