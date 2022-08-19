package com.joy.apitrial.repository

import com.joy.apitrial.api.ProductApi
import com.joy.apitrial.model.Product
import com.joy.apitrial.model.ProductResponse
import javax.inject.Inject

class ProductRepo @Inject constructor(
    private val productApi: ProductApi
) {
    suspend fun getProducts(): List<Product> {
        return productApi.getProduct().products
    }
}