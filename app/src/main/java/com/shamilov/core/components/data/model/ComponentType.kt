package com.shamilov.core.components.data.model

import com.google.gson.annotations.SerializedName

/**
 * Available component types
 */
enum class ComponentType {
    @SerializedName("header")
    header,
    @SerializedName("single_banner")
    single_banner,
    @SerializedName("banner_list")
    banner_list,
    @SerializedName("cards")
    cards,
}