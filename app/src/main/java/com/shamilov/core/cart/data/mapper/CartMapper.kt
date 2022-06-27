package com.shamilov.core.cart.data.mapper

import com.shamilov.core.cart.data.models.responses.CartItemResponse
import com.shamilov.core.cart.data.models.responses.CartProductResponse
import com.shamilov.core.cart.data.models.responses.CartResponse
import com.shamilov.core.cart.data.models.responses.OptionResponse
import com.shamilov.core.cart.domain.models.Cart
import com.shamilov.core.cart.domain.models.CartItem
import com.shamilov.core.cart.domain.models.CartProduct
import com.shamilov.core.cart.domain.models.CartOption
import javax.inject.Inject

class CartMapper @Inject constructor() {
    fun mapCartResponse(response: CartResponse): Cart {
        return Cart(
            items = response.items.map { mapCartItemResponse(it) },
            totalPrice = response.totalPrice,
        )
    }

    private fun mapCartItemResponse(response: CartItemResponse): CartItem {
        return CartItem(
            id = response.id,
            quantity = response.quantity,
            product = mapCartProductResponse(response.product),
            options = response.options.map { mapOptionResponse(it) },
        )
    }

    private fun mapCartProductResponse(response: CartProductResponse): CartProduct {
        return CartProduct(
            id = response.id,
            name = response.name,
            description = response.description,
            price = response.price,
            rating = response.rating,
        )
    }

    private fun mapOptionResponse(response: OptionResponse): CartOption {
        return CartOption(
            id = response.id,
            name = response.name,
            price = response.price,
            quantity = response.quantity,
        )
    }
}