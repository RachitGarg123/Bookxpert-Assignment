package com.bookxpert.assignment.captureImage.presentation

import android.net.Uri
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil3.compose.rememberAsyncImagePainter
import com.bookxpert.assignment.R
import com.bookxpert.assignment.captureImage.domain.captureImage
import com.bookxpert.assignment.captureImage.domain.getCameraProvider

@Composable
fun CameraPreviewScreen(
    innerPadding: PaddingValues,
    galleryImageUri: Uri?
) {
    val lensFacing = CameraSelector.LENS_FACING_BACK
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val preview = Preview.Builder().build()
    val previewView = remember {
        PreviewView(context)
    }
    val cameraxSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()
    val imageCapture = remember {
        ImageCapture.Builder().build()
    }
    var imageUri: Uri? by remember { mutableStateOf(null) }
    LaunchedEffect(lensFacing) {
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(lifecycleOwner, cameraxSelector, preview, imageCapture)
        preview.surfaceProvider = previewView.surfaceProvider
    }
    Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxSize().padding(innerPadding)) {
        if(!galleryImageUri?.toString().isNullOrEmpty()) {
            imageUri = galleryImageUri
        }
        if(imageUri != null) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberAsyncImagePainter(imageUri),
                contentDescription = stringResource(R.string.capture_image)
            )
        } else {
            AndroidView({ previewView }, modifier = Modifier.fillMaxSize())
            Button(
                modifier = Modifier.padding(20.dp),
                onClick = {
                    captureImage(
                        imageCapture,
                        context,
                        imageCaptured = { savedUri ->
                            imageUri = savedUri
                        }
                    )
                }
            ) {
                Text(text = stringResource(R.string.capture_image))
            }
        }
    }
}