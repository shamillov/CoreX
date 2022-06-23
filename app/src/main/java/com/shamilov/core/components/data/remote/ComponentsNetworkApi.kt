package com.shamilov.core.components.data.remote

import com.shamilov.core.common.models.Data
import com.shamilov.core.components.data.model.ComponentResponse
import retrofit2.http.GET

interface ComponentsNetworkApi {
    @GET("components")
    suspend fun getComponents(): Data<List<ComponentResponse>>
}