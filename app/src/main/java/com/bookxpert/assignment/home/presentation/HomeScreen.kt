package com.bookxpert.assignment.home.presentation

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Shapes
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.GetCredentialRequest
import com.bookxpert.assignment.R
import com.bookxpert.assignment.core.AssignmentBookxpertApplication
import com.google.android.libraries.identity.googleid.GetGoogleIdOption

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
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
            onClick = {},
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = stringResource(R.string.get_data))
        }

        Button(
            onClick = {},
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

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}