package com.shamilov.core.domain.repository

import com.shamilov.core.domain.model.Component

interface ComponentsRepository {
    suspend fun getComponents(): Result<List<Component>>
}