package com.bookxpert.assignment.pdfViewer.presentation

import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.bookxpert.assignment.BuildConfig

@Composable
fun PdfViewer(innerPaddingValues: PaddingValues) {
    var isWebViewLoaded by remember { mutableStateOf(false) }
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptCanOpenWindowsAutomatically = true
                webViewClient = object : WebViewClient() {
                    override fun onReceivedSslError(
                        view: WebView?,
                        handler: SslErrorHandler?,
                        error: SslError?
                    ) {
                        isWebViewLoaded = true
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        isWebViewLoaded = true
                    }
                }
                settings.domStorageEnabled = true
                settings.allowFileAccessFromFileURLs = false
                settings.allowUniversalAccessFromFileURLs = false
                settings.allowFileAccess = false
                settings.allowContentAccess = false
                getSettings().pluginState = WebSettings.PluginState.ON;
                settings.javaScriptEnabled = true
                loadUrl("${BuildConfig.PDF_BASE_URL}${BuildConfig.PDF_URL}")
            }
        },
        modifier = Modifier.fillMaxSize().padding(innerPaddingValues)
    )
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if(!isWebViewLoaded) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 5.dp
            )
        }
    }
}