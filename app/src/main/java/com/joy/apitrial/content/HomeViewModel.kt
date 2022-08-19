package com.joy.apitrial.content

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joy.apitrial.DataProvider
import com.joy.apitrial.model.Product
import com.joy.apitrial.repository.ProductRepo
import com.joy.apitrial.ui.theme.ApiTrialTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


/*@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepo: ProductRepo
) : ViewModel(){

    private val _state = MutableStateFlow(emptyList<Product>())
    val state: StateFlow<List<Product>>
    get() = _state
    init {
           viewModelScope.launch {
            val products = productRepo.getProducts()
            _state.value = products
        }
    }

}*/

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel(){
    private val _state = MutableStateFlow<List<Product>>(emptyList())
    val state: StateFlow<List<Product>>
    get() = _state

    init {
        getProducts()
    }

    fun getProducts(){
        viewModelScope.launch {
            _state.value = DataProvider.productList
        }
    }
}