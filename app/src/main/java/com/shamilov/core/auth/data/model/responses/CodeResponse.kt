package com.shamilov.core.auth.data.model.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CodeResponse(
    //temporary field
    val code: String,
    val token: String,
    @SerialName("time_left")
    val timeLeft: Int,
)