package com.shamilov.core.components.data.model

import com.google.gson.annotations.SerializedName

/**
 * Available component types
 */
enum class ComponentType {
    @SerializedName("header")
    HEADER_COMPONENT,
    @SerializedName("single_banner")
    BANNER_COMPONENT,
}