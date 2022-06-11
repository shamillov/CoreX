package com.shamilov.core.domain.model

import com.shamilov.core.data.model.ComponentResponse
import com.shamilov.core.data.model.Response

typealias Components = Result<List<ComponentResponse>>

open class Component