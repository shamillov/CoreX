package com.shamilov.core.components.data

import com.shamilov.core.components.data.model.*
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

/**
 * When a new component appears, need to add a new type here
 */
object ComponentPolymorphicDeserializer :
    JsonContentPolymorphicSerializer<ComponentResponse>(ComponentResponse::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out ComponentResponse> {
        return when (val type = element.jsonObject["type"]?.jsonPrimitive?.content) {
            ComponentType.HEADER_COMPONENT -> HeaderComponentResponse.serializer()
            ComponentType.BANNER_COMPONENT -> BannerComponentResponse.serializer()
            ComponentType.BANNERS_COMPONENT -> BannersComponentResponse.serializer()
            ComponentType.CARDS_COMPONENT -> CardsComponentResponse.serializer()
            else -> error("The $type type not supported")
        }
    }

}