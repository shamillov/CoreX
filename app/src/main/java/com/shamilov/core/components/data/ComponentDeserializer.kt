package com.shamilov.core.components.data

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.shamilov.core.components.data.model.*
import java.lang.reflect.Type

class ComponentDeserializer : JsonDeserializer<ComponentResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext
    ): ComponentResponse {
        val component = context.deserialize<ComponentResponse>(json, ComponentResponse::class.java)
        val type = getType(component.type)

        return context.deserialize<ComponentResponse>(json, type)
    }

    private fun getType(type: ComponentType?) = when (type) {
        ComponentType.HEADER_COMPONENT -> HeaderComponentResponse::class.java
        ComponentType.BANNER_COMPONENT -> BannerComponentResponse::class.java
        else -> UnknownComponentResponse::class.java
    }
}