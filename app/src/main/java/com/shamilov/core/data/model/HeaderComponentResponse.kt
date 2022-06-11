package com.shamilov.core.data.model

class HeaderComponentResponse(
    type: String,
    val title: String,
    val subtitle: String?,
) : ComponentResponse(type)