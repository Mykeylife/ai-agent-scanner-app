package com.aiagent.scanner.ui.screens

import android.Manifest
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.lifecycle.LifecycleOwner
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.io.File
import java.util.concurrent.Executors

@Composable
fun TextScannerScreen() {
    var extractedText by remember { mutableStateOf("") }
    var showCamera by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Text Scanner - Copy to Clipboard",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (showCamera) {
            CameraPreview(
                onTextExtracted = { text ->
                    extractedText = text
                    showCamera = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        } else {
            Button(
                onClick = { showCamera = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Icon(Icons.Default.PhotoCamera, "Camera", modifier = Modifier.padding(end = 8.dp))
                Text("Open Camera")
            }
        }

        if (extractedText.isNotEmpty()) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(bottom = 16.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    extractedText,
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Button(
                onClick = {
                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    clipboard.setPrimaryClip(ClipData.newPlainText("Scanned Text", extractedText))
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.ContentCopy, "Copy", modifier = Modifier.padding(end = 8.dp))
                Text("Copy to Clipboard")
            }
        }
    }
}

@Composable
fun CameraPreview(
    onTextExtracted: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalContext.current as? LifecycleOwner
    var cameraProvider: ProcessCameraProvider? = null

    LaunchedEffect(Unit) {
        val future = ProcessCameraProvider.getInstance(context)
        future.addListener({
            cameraProvider = future.result
            setupCamera(context, cameraProvider, lifecycleOwner) { text ->
                onTextExtracted(text)
            }
        }, context.mainExecutor)
    }

    AndroidView(
        factory = { ctx ->
            PreviewView(ctx).apply {
                scaleType = PreviewView.ScaleType.FILL_CENTER
            }
        },
        modifier = modifier
    )
}

private fun setupCamera(
    context: Context,
    cameraProvider: ProcessCameraProvider?,
    lifecycleOwner: LifecycleOwner?,
    onTextExtracted: (String) -> Unit
) {
    if (cameraProvider == null || lifecycleOwner == null) return

    val imageCapture = ImageCapture.Builder().build()
    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

    try {
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, imageCapture)

        // Auto-capture after delay
        val file = File(context.cacheDir, "scan_${System.currentTimeMillis()}.jpg")
        val outputOptions = ImageCapture.OutputFileOptions.Builder(file).build()

        imageCapture.takePicture(
            outputOptions,
            Executors.newSingleThreadExecutor(),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    extractTextFromFile(file) { text ->
                        onTextExtracted(text)
                    }
                }

                override fun onError(exception: ImageCaptureException) {
                    exception.printStackTrace()
                }
            }
        )
    } catch (exc: Exception) {
        exc.printStackTrace()
    }
}

private fun extractTextFromFile(
    file: File,
    onTextExtracted: (String) -> Unit
) {
    val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
    try {
        val image = InputImage.fromFilePath(file.absolutePath)
        recognizer.process(image)
            .addOnSuccessListener { visionText ->
                onTextExtracted(visionText.text)
            }
            .addOnFailureListener {
                it.printStackTrace()
            }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

@Composable
fun AndroidView(
    factory: (Context) -> android.view.View,
    modifier: Modifier = Modifier
) {
    androidx.compose.ui.viewinterop.AndroidView(
        factory = factory,
        modifier = modifier
    )
}
