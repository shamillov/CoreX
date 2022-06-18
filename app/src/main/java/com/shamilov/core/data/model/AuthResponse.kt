package com.shamilov.core.data.model

class AuthResponse(
    //code: temporary field for auth
    val code: String,
    val token: String,
    val timeLeft: Int,
)