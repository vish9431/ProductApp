package com.example.chatwise.data

import com.example.chatwise.model.ProductResponse
import retrofit2.http.GET


interface ProductService {
    @GET("products")
    suspend fun getProducts(): ProductResponse // Define appropriate response type based on your API
}
