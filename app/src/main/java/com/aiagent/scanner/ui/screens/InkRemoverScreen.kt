package com.aiagent.scanner.ui.screens

import android.Manifest
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Download
import com.aiagent.scanner.utils.InkRemovalProcessor
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.io.File
import java.io.FileOutputStream

@Composable
fun InkRemoverScreen() {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var processedBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var isProcessing by remember { mutableStateOf(false) }
    var extractedText by remember { mutableStateOf("") }
    val context = LocalContext.current

    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            selectedImageUri = null
            isProcessing = true
            processImageWithAI(bitmap) { cleaned ->
                processedBitmap = cleaned
                isProcessing = false
                extractTextFromBitmap(cleaned) { text ->
                    extractedText = text
                }
            }
        }
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            selectedImageUri = uri
            isProcessing = true
            val bitmap = context.contentResolver.openInputStream(uri)?.use {
                BitmapFactory.decodeStream(it)
            }
            if (bitmap != null) {
                processImageWithAI(bitmap) { cleaned ->
                    processedBitmap = cleaned
                    isProcessing = false
                    extractTextFromBitmap(cleaned) { text ->
                        extractedText = text
                    }
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Ink Remover - AI Correction Fluid",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { cameraLauncher.launch(null) },
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Default.PhotoCamera, "Camera", modifier = Modifier.padding(end = 8.dp))
                Text("Camera")
            }
            Button(
                onClick = { galleryLauncher.launch("image/*") },
                modifier = Modifier.weight(1f)
            ) {
                Text("Gallery")
            }
        }

        if (isProcessing) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(androidx.compose.ui.Alignment.CenterHorizontally)
                    .padding(32.dp)
            )
        }

        if (processedBitmap != null) {
            Text(
                "Cleaned Image:",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Image(
                bitmap = processedBitmap!!.asImageBitmap(),
                contentDescription = "Processed",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(bottom = 16.dp)
            )

            if (extractedText.isNotEmpty()) {
                Text(
                    "Extracted Text:",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(bottom = 16.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Text(
                        extractedText,
                        modifier = Modifier.padding(12.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Button(
                onClick = {
                    val file = File(context.cacheDir, "cleaned_${System.currentTimeMillis()}.png")
                    FileOutputStream(file).use { out ->
                        processedBitmap!!.compress(Bitmap.CompressFormat.PNG, 100, out)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Download, "Save", modifier = Modifier.padding(end = 8.dp))
                Text("Save Cleaned Image")
            }
        }
    }
}

private fun processImageWithAI(
    bitmap: Bitmap,
    onProcessed: (Bitmap) -> Unit
) {
    val processor = InkRemovalProcessor()
    val cleaned = processor.removeInk(bitmap)
    onProcessed(cleaned)
}

private fun extractTextFromBitmap(
    bitmap: Bitmap,
    onTextExtracted: (String) -> Unit
) {
    val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
    try {
        val image = InputImage.fromBitmap(bitmap, 0)
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
