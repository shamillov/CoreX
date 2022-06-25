package com.shamilov.core.cart.domain.repository

import com.shamilov.core.cart.domain.models.Cart

interface CartRepository {
    suspend fun getCart(): Result<Cart>
}