package com.example.chatwise.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatwise.R
import com.example.chatwise.data.ProductRepository
import com.example.chatwise.util.RetrofitClient

class ProductListActivity : AppCompatActivity() {

    private lateinit var productViewModel: ProductViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        // Initialize Retrofit service and repository
        val productService = RetrofitClient.productService
        val productRepository = ProductRepository(productService)

        // Initialize ProductViewModel using ViewModelProvider with ProductViewModelFactory
        val productViewModelFactory = ProductViewModelFactory(productRepository)
        productViewModel = ViewModelProvider(this, productViewModelFactory)
            .get(ProductViewModel::class.java)

        //Progress Bar
        progressBar = findViewById(R.id.progressBar)

        // Observe productList LiveData to update RecyclerView
        productViewModel.productList.observe(this) { products ->
            productAdapter.submitList(products)
            // Data loaded, hide progress bar
            progressBar.visibility = View.GONE
        }

        // Show progress bar while data is loading
        progressBar.visibility = View.VISIBLE
        productViewModel.fetchProducts()



        // Set up RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        productAdapter = ProductAdapter()
        recyclerView.adapter = productAdapter

        // Observe productList LiveData in ProductViewModel
        productViewModel.productList.observe(this) { products ->
            productAdapter.submitList(products)
        }

        // Fetch products when activity is created or resumed
        productViewModel.fetchProducts()
    }
}