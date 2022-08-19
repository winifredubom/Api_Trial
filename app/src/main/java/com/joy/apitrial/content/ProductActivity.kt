package com.joy.apitrial.content

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.joy.apitrial.model.Product
import com.joy.apitrial.ui.theme.ApiTrialTheme

class ProductActivity: ComponentActivity() {

    private val product: Product by lazy{
        intent?.getSerializableExtra(PRODUCTS) as Product
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            ApiTrialTheme {
                HomeContent(product = product )
            }
        }
    }
    companion object{
        private const val PRODUCTS = "product"
        fun newIntent(context: Context, product: Product) =
            Intent(context, ProductActivity::class.java).apply {
                putExtra(PRODUCTS, product)
            }
    }
}