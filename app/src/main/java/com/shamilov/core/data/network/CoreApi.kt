package com.shamilov.core.data.network

import com.shamilov.core.data.model.ComponentResponse
import com.shamilov.core.data.model.Response

typealias Components = Result<Response<List<ComponentResponse>>>

interface CoreApi {
    suspend fun getComponents(): Components
}