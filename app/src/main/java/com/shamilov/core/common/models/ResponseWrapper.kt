package com.shamilov.core.common.models

import kotlinx.serialization.Serializable
import retrofit2.Response

@Serializable
class ResponseWrapper<T>(
    val data: T
)

typealias Data<T> = Response<ResponseWrapper<T>>