package com.george.firebaseapp.ui.theme.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.george.firebaseapp.R
import com.george.firebaseapp.navigation.ROUTE_ADD_PRODUCT
import com.george.firebaseapp.navigation.ROUTE_VIEW_PRODUCT
import com.george.firebaseapp.navigation.ROUTE_VIEW_UPLOAD


@Composable
fun HomeScreen(navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var context= LocalContext.current
//        var productdata=productviewmodel(navController,context)
        Spacer(modifier = Modifier.height(50.dp))


        Text(text = "Welcome to Home page",
            color = Color.Red,
            fontFamily = FontFamily.Cursive,
            fontSize = 30.sp)
        Image(painter = painterResource(id=R.drawable.fire), contentDescription = "fire")



        Spacer(modifier = Modifier.height(50.dp))



        Button(onClick = {
            navController.navigate(ROUTE_ADD_PRODUCT)
        },modifier = Modifier.fillMaxWidth()) {
            Text(text = "Add Product")
        }
        Spacer(modifier = Modifier.height(100.dp))

        Button(onClick = {
            navController.navigate(ROUTE_VIEW_PRODUCT)
        },modifier = Modifier.fillMaxWidth()) {
            Text(text = "View Product")
        }
        Spacer(modifier = Modifier.height(100.dp))

        Button(onClick = {
            navController.navigate(ROUTE_VIEW_UPLOAD)
        },modifier = Modifier.fillMaxWidth()) {
            Text(text = "View Products")
        }


    }



}

private fun NavHostController.navigate(value: Any) {}

@Preview
@Composable
fun Homepreview() {
    HomeScreen(rememberNavController())
}