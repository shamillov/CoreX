package com.shamilov.core.components.domain.repository

import com.shamilov.core.components.domain.model.Component

interface ComponentsRepository {
    suspend fun getComponents(): Result<List<Component>>
}