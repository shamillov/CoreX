package com.shamilov.core.components.data.mapper

import com.shamilov.core.components.data.model.HeaderComponentResponse
import com.shamilov.core.components.domain.model.HeaderComponent

class HeaderComponentMapper {
    fun mapHeaderComponent(response: HeaderComponentResponse): HeaderComponent {
        return HeaderComponent(
            title = response.title,
            subtitle = response.subtitle,
        )
    }
}