package com.shamilov.core.auth.data.model.requests

import kotlinx.serialization.Serializable

@Serializable
class UuidRequest(
    val uuid: String,
)