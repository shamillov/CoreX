package com.shamilov.core.components.data.model

import kotlinx.serialization.Serializable

@Serializable
class HeaderComponentResponse(
    override val type: String?,
    val title: String,
    val subtitle: String?,
) : ComponentResponse()