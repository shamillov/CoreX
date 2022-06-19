package com.shamilov.core.auth.data.model.responses

import com.google.gson.annotations.SerializedName

class CodeResponse(
    //temporary field
    val code: String,
    val token: String,
    @SerializedName("time_left")
    val timeLeft: String,
)