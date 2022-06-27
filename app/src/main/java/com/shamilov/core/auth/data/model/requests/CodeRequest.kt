package com.shamilov.core.auth.data.model.requests

import kotlinx.serialization.Serializable

@Serializable
class CodeRequest(
    val code: String,
    val token: String,
)