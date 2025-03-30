package com.bookxpert.assignment.objectDetails.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.bookxpert.assignment.core.utility.LogType
import com.bookxpert.assignment.core.utility.printLog
import com.bookxpert.assignment.home.presentation.HomeViewModel

@Composable
fun ObjectDataScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel
) {
    LaunchedEffect(Unit) {
        printLog(LogType.DEBUG, "roomDB", "launchedEffect")
        homeViewModel.getObjectsFromLocal()
    }
    LazyColumn() {
        items(items = homeViewModel.localDataResponse) { objectData ->
            ObjectDataItem(objectData)
        }
    }
}