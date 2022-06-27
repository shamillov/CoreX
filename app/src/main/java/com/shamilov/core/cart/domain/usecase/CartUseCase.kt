package com.shamilov.core.cart.domain.usecase

import com.shamilov.core.cart.domain.models.Cart
import com.shamilov.core.cart.domain.repository.CartRepository
import javax.inject.Inject

interface CartUseCase {
    suspend fun getCart(): Result<Cart>
}

class CartUseCaseImpl @Inject constructor(
    private val repository: CartRepository,
) : CartUseCase {
    override suspend fun getCart(): Result<Cart> {
        return repository.getCart()
    }
}