package com.shamilov.core.components.data.model

import kotlinx.serialization.Serializable

/**
 * Not used
 */
@Serializable
class UnknownComponentResponse(
    override val type: String?,
) : ComponentResponse()