package com.joy.apitrial.content


import android.text.TextUtils.indexOf
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.joy.apitrial.DataProvider.productList
import com.joy.apitrial.model.Product
import com.joy.apitrial.ui.theme.Purple500


@Composable
fun HomeContent( product: Product) {
    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints() {
            Surface() {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                    Arrangement.SpaceBetween) {
                    ContentImage(product = product, containerHeight = this@BoxWithConstraints.maxHeight)
                    ContentProfile(containerHeight = this@BoxWithConstraints.maxHeight, product = product)
                }
            }

        }
    }
}

@Composable
private fun ContentImage(product: Product, containerHeight: Dp) {
    val imagePainter = rememberImagePainter(data = product.thumbnail)
    Column(modifier = Modifier
        .padding(10.dp)
        .clip(RoundedCornerShape(9.dp))) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(bottom = 10.dp),
            painter = imagePainter,
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )

        Spacer(modifier = Modifier.padding(bottom = 3.dp))
    }
}


@Composable
fun ContentProfile(containerHeight: Dp, product: Product) {
    Column(modifier = Modifier.padding(16.dp)) {
        ImageRow(product = product)
        ContentTitle(product = product)
        ContentProperty(label = "Brand: ${product.brand}")
        Spacer(modifier = Modifier.padding(5.dp))
        ContentProperty(label = "Description: ${product.description} ")
        Spacer(modifier = Modifier.padding(5.dp))
        ContentProperty(label = "Price: ${product.price}")
        Spacer(modifier = Modifier.padding(5.dp))
        RatingBarView(product = product)
        

        Spacer(modifier = Modifier.height((containerHeight).coerceAtLeast(0.dp)))
    }
}

@Composable
private fun  ContentTitle(product: Product){
    Column(modifier = Modifier.padding(top = 4.dp, bottom = 5.dp, start = 10.dp)) {
        Text(text = product.title,
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.W400
        )
    }
}

@Composable
private fun ContentProperty(label: String){
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = label,
            modifier = Modifier.height(20.dp),
            style = MaterialTheme.typography.subtitle1)

    }
}

@Composable
fun ImageRow(product: Product) {

    val images = remember { product.images }
    Column(verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {

        LazyRow(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)){
            items(
                items = images,
                itemContent = {
                    PictureCard(picture = it)
                }
            )
        }
    }

}

@Composable
fun PictureCard(picture: String) {
        Box(modifier = Modifier
            .height(60.dp)
            .width(62.dp)
            ) {
        Image(painter = rememberAsyncImagePainter(picture),
            contentDescription = "",
        modifier = Modifier
            .clip(
                RoundedCornerShape(20)
            ).size(50.dp, 50.dp).border(color = Color.Magenta, width = 3.dp, shape = RoundedCornerShape(20)), contentScale = ContentScale.FillBounds
        )
    }

}







