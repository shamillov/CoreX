package com.shamilov.core.common.models

import retrofit2.Response

class ResponseWrapper<T>(
    val data: T
)

typealias Data<T> = Response<ResponseWrapper<T>>