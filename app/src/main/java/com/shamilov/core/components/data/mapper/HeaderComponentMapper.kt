package com.shamilov.core.components.data.mapper

import com.shamilov.core.components.data.model.HeaderComponentResponse
import com.shamilov.core.components.domain.model.HeaderComponent

class HeaderComponentMapper {
    fun mapHeaderComponent(component: HeaderComponentResponse): HeaderComponent {
        return HeaderComponent(
            title = component.title,
            subtitle = component.subtitle,
        )
    }
}