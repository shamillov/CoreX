package com.shamilov.core.cart.data.repository

import com.shamilov.core.cart.data.mapper.CartMapper
import com.shamilov.core.cart.data.remote.CartNetworkApi
import com.shamilov.core.cart.domain.models.Cart
import com.shamilov.core.cart.domain.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val api: CartNetworkApi,
    private val mapper: CartMapper,
) : CartRepository {
    override suspend fun getCart(): Result<Cart> {
        return try {
            val response = api.getCart()
            val body = response.body()

            if (response.isSuccessful && body != null) {
                val result = mapper.mapCartResponse(body.data)
                Result.success(result)
            } else {
                error(response.message())
            }
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}