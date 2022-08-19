package com.joy.apitrial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.joy.apitrial.content.Home
import com.joy.apitrial.content.ProductActivity
import com.joy.apitrial.model.Product
import com.joy.apitrial.ui.theme.ApiTrialTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiTrialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation1(navigateToProfile = {startActivity(ProductActivity.newIntent(this, it))})

                }
            }
        }
    }

}

       @Composable
       fun Navigation1(navigateToProfile: (Product) -> Unit) {
           val navController = rememberNavController()
           NavHost(navController = navController, startDestination = "/fullApp")
           {
               composable("/fullApp") {
                   FullApp(navigateToProfile = navigateToProfile)
               }
           }
       }


       @Composable
       fun FullApp(navigateToProfile: (Product) -> Unit) {
           Scaffold(content = {
               Home(navigateToProfile = navigateToProfile)
           })
       }
   
