package com.shamilov.core.cart.data.models.responses

import com.google.gson.annotations.SerializedName

class CartResponse(
    val items: List<CartItemResponse>,
    @SerializedName("total_price")
    val totalPrice: Int,
)

class CartItemResponse(
    val id: Int,
    val quantity: Int,
    val product: CartProductResponse,
    val options: List<OptionResponse>,
)

class CartProductResponse(
    val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    val rating: Int,
    val media: Any,
)

class OptionResponse(
    val id: Int,
    val name: String,
    val price: Int,
    val quantity: Int,
)