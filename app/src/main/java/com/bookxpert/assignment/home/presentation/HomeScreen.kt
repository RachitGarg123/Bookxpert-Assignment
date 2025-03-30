package com.bookxpert.assignment.home.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bookxpert.assignment.R
import androidx.compose.runtime.getValue
import com.bookxpert.assignment.core.navigation.Screen
import com.bookxpert.assignment.core.utility.showToast

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    homeViewModel: HomeViewModel
) {
    val objectsResponse by  homeViewModel.objectsResponse.collectAsState()
    val isLoading by  homeViewModel.isLoading.collectAsState()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        if(isLoading) {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        if(objectsResponse.isNotEmpty()) {
            showToast("Api Success", Toast.LENGTH_SHORT)
            homeViewModel.insertAllObjectsData(objectsResponse)
        }
        Button(
            onClick = {},
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = stringResource(R.string.view_pdf))
        }

        Button(
            onClick = {},
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = stringResource(R.string.capture_image))
        }

        Button(
            onClick = {
                homeViewModel.getObjects()
            },
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = stringResource(R.string.get_data))
        }
        val localDataResponse by homeViewModel.localDataResponse.collectAsState()
        if(localDataResponse.isNotEmpty()) {
            showToast(homeViewModel.localDataResponse.toString(), Toast.LENGTH_LONG)
        }

        Button(
            onClick = {
                navHostController.navigate(Screen.Objects.route)
            },
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = stringResource(R.string.view_data))
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.push_notifications),
                modifier = modifier,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier.width(20.dp))
            Switch(
                checked = true,
                onCheckedChange = {

                }
            )
        }
    }
}