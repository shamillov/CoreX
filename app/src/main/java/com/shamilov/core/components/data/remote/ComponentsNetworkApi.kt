package com.shamilov.core.components.data.remote

import com.shamilov.core.common.models.Data
import com.shamilov.core.components.data.model.ComponentResponse

interface ComponentsNetworkApi {
    suspend fun getComponents(): Data<List<ComponentResponse>>
}