package com.bookxpert.assignment.objectDetails.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bookxpert.assignment.home.presentation.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObjectDataScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    objectsViewModel: ObjectsViewModel,
    innerPadding: PaddingValues
) {
    val localDataResponse by objectsViewModel.localDataResponse.collectAsState()
    LaunchedEffect(Unit) {
        objectsViewModel.getObjectsFromLocal()
    }
    LazyColumn(modifier = modifier.padding(innerPadding).padding(vertical = 10.dp)) {
        items(items = localDataResponse) { objectData ->
            val dismissState = rememberSwipeToDismissBoxState(
                confirmValueChange = {
                    when (it) {
                        SwipeToDismissBoxValue.StartToEnd -> { // Swipe Left -> Delete
                            objectsViewModel.editLocalObjectData(objectData)
                            true
                        }
                        SwipeToDismissBoxValue.EndToStart -> { // Swipe Right -> Edit
                            objectsViewModel.deleteLocalObjectData(objectData)
                            true
                        }
                        else -> false
                    }
                }
            )
            SwipeToDismissBox(
                state = dismissState,
                backgroundContent = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        contentAlignment = when (dismissState.targetValue) {
                            SwipeToDismissBoxValue.EndToStart -> Alignment.CenterEnd  // Delete Icon on Right
                            SwipeToDismissBoxValue.StartToEnd -> Alignment.CenterStart // Edit Icon on Left
                            else -> Alignment.Center
                        }
                    ) {
                        if (dismissState.targetValue == SwipeToDismissBoxValue.EndToStart) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = MaterialTheme.colorScheme.error,
                                modifier = Modifier.padding(8.dp)
                            )
                        } else if (dismissState.targetValue == SwipeToDismissBoxValue.StartToEnd) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit",
                                tint = MaterialTheme.colorScheme.surfaceBright,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            ) {
                ObjectDataItem(objectData)
            }
        }
    }
}