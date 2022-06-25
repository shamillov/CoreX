package com.shamilov.core.cart.data.remote

import com.shamilov.core.cart.data.models.responses.CartResponse
import com.shamilov.core.common.models.Data
import retrofit2.http.GET
import retrofit2.http.POST

interface CartNetworkApi {

    @GET("cart")
    suspend fun getCart(): Data<CartResponse>

    @POST("cart-item")
    suspend fun addProduct()

}