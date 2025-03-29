package com.bookxpert.assignment.signIn.presentation

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bookxpert.assignment.R
import com.bookxpert.assignment.core.utility.LogType
import com.bookxpert.assignment.core.utility.printLog

@Composable
fun GoogleSignInScreen(
    modifier: Modifier = Modifier,
    clicked: Boolean,
    onGoogleSignIn: () -> Unit,
    onGoogleSignInButtonClicked: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shape = MaterialTheme.shapes.small,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            onClick = { onGoogleSignInButtonClicked() }
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 12.dp, top = 12.dp, end = 16.dp, bottom = 12.dp)
                    .animateContentSize(
                        animationSpec = tween(durationMillis = 200)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_google_logo),
                    contentDescription = stringResource(R.string.google_icon),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(R.string.sign_up_with_google))
                if (clicked) {
                    printLog(LogType.DEBUG, "googleSignIn","isClicked")
                    Spacer(modifier = Modifier.width(16.dp))
                    CircularProgressIndicator(
                        modifier = Modifier.width(20.dp).height(20.dp),
                        strokeWidth = 2.dp
                    )
                    onGoogleSignIn()
                }
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shape = MaterialTheme.shapes.small,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 12.dp, top = 12.dp, end = 16.dp, bottom = 12.dp)
                    .animateContentSize(
                        animationSpec = tween(durationMillis = 200)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = stringResource(R.string.person_placeholder),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(R.string.continue_as_a_guest_user))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GoogleSignInScreenPreview() {
    GoogleSignInScreen(
        clicked = false,
        onGoogleSignIn = {},
        onGoogleSignInButtonClicked = {}
    )
}