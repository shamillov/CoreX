package com.shamilov.core.components.data.model

import com.shamilov.core.components.data.ComponentPolymorphicDeserializer
import kotlinx.serialization.Serializable

/**
 * Basic component for all components
 */
@Serializable(with = ComponentPolymorphicDeserializer::class)
sealed class ComponentResponse {
    abstract val type: String?
}