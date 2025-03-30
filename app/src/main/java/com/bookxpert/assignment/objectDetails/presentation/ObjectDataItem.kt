package com.bookxpert.assignment.objectDetails.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bookxpert.assignment.home.data.ObjectDetails
import com.bookxpert.assignment.home.data.Objects

@Composable
fun ObjectDataItem(
    objects: Objects,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = objects.name ?: "--",
            maxLines = 2
        )
        Column() {

        }
    }
}

@Preview
@Composable
private fun ObjectDataItemPreview() {
    ObjectDataItem(Objects(id = "", name = "", data = ObjectDetails(color = "", capacity = "")))
}