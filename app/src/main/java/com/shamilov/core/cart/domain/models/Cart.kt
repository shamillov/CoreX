package com.shamilov.core.cart.domain.models

data class Cart(
    val items: List<CartItem>,
    val totalPrice: Int,
)

data class CartItem(
    val id: Int,
    val quantity: Int,
    val product: CartProduct,
    val options: List<CartOption>,
)

data class CartProduct(
    val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    val rating: Int,
    val media: Any,
)

data class CartOption(
    val id: Int,
    val name: String,
    val price: Int,
    val quantity: Int,
)