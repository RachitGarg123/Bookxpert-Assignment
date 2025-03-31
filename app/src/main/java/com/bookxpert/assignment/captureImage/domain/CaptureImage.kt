package com.bookxpert.assignment.captureImage.domain

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.core.content.ContextCompat
import com.bookxpert.assignment.core.constants.AppConstants
import com.bookxpert.assignment.core.utility.LogType
import com.bookxpert.assignment.core.utility.printLog
import com.bookxpert.assignment.core.utility.showToast

fun captureImage(imageCapture: ImageCapture, context: Context, imageCaptured: (Uri?)-> Unit) {
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, AppConstants.CAMERAX_NAME)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
        }
    }
    val outputOptions = ImageCapture.OutputFileOptions.Builder(
        context.contentResolver,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        contentValues
    ).build()
    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                printLog(LogType.DEBUG, "cameraX", "savedImageUri ---> ${outputFileResults.savedUri}")
                imageCaptured(outputFileResults.savedUri)
            }
            override fun onError(exception: ImageCaptureException) {
                showToast("exception message: ${exception.message}", Toast.LENGTH_SHORT)
            }
        })
}