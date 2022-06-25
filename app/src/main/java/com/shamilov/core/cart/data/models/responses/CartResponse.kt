package com.shamilov.core.cart.data.models.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CartResponse(
    val items: List<CartItemResponse>,
    @SerialName("total_price")
    val totalPrice: Int,
)

@Serializable
class CartItemResponse(
    val id: Int,
    val quantity: Int,
    val product: CartProductResponse,
    val options: List<OptionResponse>,
)

@Serializable
class CartProductResponse(
    val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    val rating: Int,
)

@Serializable
class OptionResponse(
    val id: Int,
    val name: String,
    val price: Int,
    val quantity: Int,
)