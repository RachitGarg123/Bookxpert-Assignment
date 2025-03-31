package com.bookxpert.assignment.captureImage.presentation

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import coil3.compose.rememberAsyncImagePainter
import com.bookxpert.assignment.R
import com.bookxpert.assignment.core.navigation.Screen
import com.bookxpert.assignment.core.utility.LogType
import com.bookxpert.assignment.core.utility.createImageFile
import com.bookxpert.assignment.core.utility.printLog

@Composable
fun CameraGallerySelector(
    navHostController: NavHostController,
    onDismiss: () -> Unit = {}
) {
    val context = LocalContext.current
    val activity = context as? Activity
    val bitmap: MutableState<Bitmap?> = remember { mutableStateOf(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var tempUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                imageUri = tempUri
                Toast.makeText(context, "Image Captured! uri ---> $imageUri", Toast.LENGTH_SHORT).show()
            } else {
                tempUri = null
            }
        }
    )

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                val uri = createImageFile(context)
                tempUri = uri
                launcher.launch(uri)
            } else {
                Toast.makeText(context, "Camera permission denied!", Toast.LENGTH_SHORT).show()
            }
        }
    )
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.surfaceContainer,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 15.dp, horizontal = 10.dp).clickable {
                        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                            navHostController.navigate(Screen.CameraXPreview.route) {}
//                            val uri = createImageFile(context)
//                            imageUri = uri
//                            launcher.launch(uri)
                        } else {
                            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
                        }
                    },
                    text = stringResource(R.string.camera),
                    fontSize = 20.sp
                )
                Text(
                    modifier = Modifier.padding(vertical = 15.dp, horizontal = 10.dp).clickable {  },
                    text = stringResource(R.string.gallery),
                    fontSize = 20.sp
                )
                imageUri?.let { uri ->
//                    LaunchedEffect(uri) {
//                        printLog(LogType.DEBUG, "imageVector", "uri ----> $uri")
//                        bitmap.value = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                            ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, uri))
//                        } else {
//                            MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
//                        }
//                    }
//                    Toast.makeText(context, it.path, Toast.LENGTH_LONG).show()
//                    bitmap.value?.let { bmp ->
//                    imageUri?.let { uri ->
                        Image(
                            painter = rememberAsyncImagePainter(uri),
//                            bitmap = bmp.asImageBitmap(),
                            contentDescription = "Captured Image",
                            modifier = Modifier
                                .size(200.dp)
                                .padding(top = 16.dp)
                        )
//                    }
                }
            }
        }
    }
}