package com.shamilov.core.components.data.repository

import com.shamilov.core.components.data.mapper.ComponentsMapper
import com.shamilov.core.components.data.remote.ComponentsNetworkApi
import com.shamilov.core.components.domain.model.Component
import com.shamilov.core.components.domain.repository.ComponentsRepository
import javax.inject.Inject

class ComponentsRepositoryImpl @Inject constructor(
    private val api: ComponentsNetworkApi,
    private val mapper: ComponentsMapper,
) : ComponentsRepository {
    override suspend fun getComponents(): Result<List<Component>> {
        return try {
            val response = api.getComponents()
            val body = response.body()

            return if (response.isSuccessful && body != null) {
                val components = mapper.mapComponents(body.data)
                Result.success(components)
            } else {
                error(response.message())
            }
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}