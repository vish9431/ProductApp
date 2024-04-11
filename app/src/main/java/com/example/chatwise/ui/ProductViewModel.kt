package com.example.chatwise.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatwise.data.ProductRepository
import com.example.chatwise.model.Product
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {

    val productList = MutableLiveData<List<Product>>()

    init {
        fetchProducts()
    }

    internal fun fetchProducts() {
        viewModelScope.launch {
            try {
                val products = productRepository.getProducts()
                productList.postValue(products)
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error fetching products: ${e.message}")
                productList.postValue(emptyList()) // Handle error by providing an empty list
            }
        }
    }
}
