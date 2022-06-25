package com.shamilov.core.cart.domain.usecase

import com.shamilov.core.cart.domain.models.Cart
import com.shamilov.core.cart.domain.repository.CartRepository

interface CartUseCase {
    suspend fun getCart(): Result<Cart>
}

class CartUseCaseImpl(
    private val repository: CartRepository,
) : CartUseCase {
    override suspend fun getCart(): Result<Cart> {
        return repository.getCart()
    }
}