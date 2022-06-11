package com.shamilov.core.data.mapper

import com.shamilov.core.data.model.HeaderComponentResponse
import com.shamilov.core.domain.model.HeaderComponent

class HeaderComponentMapper {
    fun mapHeaderComponent(component: HeaderComponentResponse): HeaderComponent {
        return HeaderComponent(
            title = component.title,
            subtitle = component.subtitle,
        )
    }
}