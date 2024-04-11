package com.example.chatwise.data

import com.example.chatwise.model.Product
import kotlinx.coroutines.withContext

class ProductRepository(private val productService: ProductService) {

    suspend fun getProducts(): List<Product> {
        return productService.getProducts().products
        }
    }
