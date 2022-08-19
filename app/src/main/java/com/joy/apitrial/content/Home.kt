package com.joy.apitrial.content

import android.view.MotionEvent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.joy.apitrial.DataProvider
import com.joy.apitrial.model.Product
import com.joy.apitrial.ui.theme.Purple200

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home(navigateToProfile: (Product) -> Unit) {
    val productList = remember { DataProvider.productList }

    LazyVerticalGrid(cells = GridCells.Fixed(2)) {
        if (productList.isEmpty()) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }
        }

        items(
            items = productList,
            itemContent = {
                ProductDisplay(product = it, navigateToProfile)
            })
    }
}

@Composable
fun ProductDisplay(product: Product, navigateToProfile: (Product) -> Unit) {
    val imagePainter = rememberAsyncImagePainter(model = product.thumbnail)
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .height(370.dp)
            .fillMaxWidth()
            .padding(13.dp),
        contentColor = Color.DarkGray,
        elevation = 20.dp
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            Column(modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = { navigateToProfile(product) })
            ) {
                Image(
                    painter = imagePainter,
                    contentDescription = null,
                    modifier = Modifier
                        .height(150.dp),
                    contentScale = ContentScale.FillHeight
                )

                Surface(
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Start),
                    contentColor = MaterialTheme.colors.surface,

                    ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {

                        Text(
                            text = "${product.title}",
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold,
                            maxLines = 1
                        )
                        Divider(modifier = Modifier.padding(7.dp))

                        Text(
                            text = "Price: ${product.price}Naira",
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Divider(modifier = Modifier.padding(7.dp))


                        RatingBarView(product = product)

                        Divider(modifier = Modifier.padding(7.dp))
                        OutlinedButton(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.White),
                            border = BorderStroke(3.dp, color = Purple200),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp)
                        ) {

                            Text(
                                text = "ADD TO CART",
                                fontSize = 12.sp
                            )
                        }


                    }
                }
            }
        Customs(product= product)
        }
//        OvalShape(product = product)

    }

}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RatingBarView(
    modifier: Modifier = Modifier,
    rating: MutableState<Int> = mutableStateOf(0),
    isRatingEditable: Boolean = false,
    starSize: Dp = 20.dp,
    starsPadding: Dp = 4.dp,
    isViewAnimated: Boolean = true,
    numberOfStars: Int = 5,
    starIcon: Painter = painterResource(id = android.R.drawable.star_off),
    contentDescription: String = "rating",
    ratedStarsColor: Color = Color.Yellow,
    unRatedStarsColor: Color = Color.DarkGray,
    product: Product,
) {

    var selected by remember {
        mutableStateOf(false)
    }
    val size by animateDpAsState(
        targetValue = if (selected) (starSize) else starSize,
        spring(Spring.DampingRatioLowBouncy),
    )

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        for (star in 1..numberOfStars) {
            Icon(
                painter = starIcon,
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(if (isViewAnimated) size else starSize)
                    .padding(end = starsPadding)
                    .pointerInteropFilter {
                        when (isRatingEditable) {
                            true -> {
                                when (it.action) {
                                    MotionEvent.ACTION_DOWN -> {
                                        selected = true
                                        rating.value = star
                                    }
                                    MotionEvent.ACTION_UP -> {
                                        selected = false
                                    }
                                    MotionEvent.ACTION_CANCEL -> {
                                        selected = false
                                    }
                                }
                            }
                            false -> {}
                        }
                        isRatingEditable
                    },

                tint = if (star <= product.rating) ratedStarsColor else unRatedStarsColor
            )
        }
        Text(
            text = " ${product.rating}",
            color = Color.Black,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun Customs(product: Product) {
    Box(
        modifier = Modifier.padding(top = 10.dp)
            .height(30.dp).width(100.dp)
            .clip(
                shape = RoundedCornerShape(
                    topStart = 0.dp, topEnd = 15.dp, bottomEnd = 15.dp, bottomStart = 0.dp
                )
            )
            .background(color = Color.Red)
            .padding(start = 5.dp)
    ) {
        Text(
            text = " ${product.discountPercentage}% off",
            color = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterStart)
        )
    }
}

