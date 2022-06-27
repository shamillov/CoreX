package com.shamilov.core.auth.data.model.requests

import kotlinx.serialization.Serializable

@Serializable
class PhoneRequest(
    val phone: String,
)