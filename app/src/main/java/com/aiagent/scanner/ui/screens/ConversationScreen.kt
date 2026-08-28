package com.aiagent.scanner.ui.screens

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Bluetooth
import com.aiagent.scanner.utils.ConversationManager
import com.aiagent.scanner.utils.BluetoothAudioManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class Message(
    val text: String,
    val isUser: Boolean
)

@Composable
fun ConversationScreen() {
    var messages by remember { mutableStateOf(listOf<Message>()) }
    var userInput by remember { mutableStateOf("") }
    var isListening by remember { mutableStateOf(false) }
    var isBluetoothConnected by remember { mutableStateOf(false) }
    var isSpeaking by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val conversationManager = remember { ConversationManager(context) }
    val bluetoothManager = remember { BluetoothAudioManager(context) }

    LaunchedEffect(Unit) {
        bluetoothManager.setOnConnectionStateChanged { connected ->
            isBluetoothConnected = connected
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "AI Conversation Assistant",
                style = MaterialTheme.typography.headlineMedium
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    Icons.Default.Bluetooth,
                    "Bluetooth",
                    tint = if (isBluetoothConnected) Color.Green else Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    if (isBluetoothConnected) "Connected" else "Disconnected",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }

        // Messages
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            reverseLayout = true
        ) {
            items(messages.reversed()) { message ->
                MessageBubble(message)
            }
        }

        // Input Area
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = userInput,
                onValueChange = { userInput = it },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                placeholder = { Text("Type or speak...") },
                singleLine = true
            )

            IconButton(
                onClick = {
                    isListening = !isListening
                    if (isListening) {
                        startSpeechRecognition(context) { text ->
                            userInput = text
                            isListening = false
                        }
                    }
                }
            ) {
                Icon(
                    Icons.Default.Mic,
                    "Mic",
                    tint = if (isListening) Color.Red else Color.Gray
                )
            }

            IconButton(
                onClick = {
                    if (userInput.isNotEmpty()) {
                        messages = messages + Message(userInput, true)
                        val inputText = userInput
                        userInput = ""

                        scope.launch(Dispatchers.Main) {
                            isSpeaking = true
                            val response = conversationManager.getResponse(inputText)
                            messages = messages + Message(response, false)

                            bluetoothManager.speak(response) {
                                isSpeaking = false
                            }
                        }
                    }
                }
            ) {
                Icon(Icons.Default.Send, "Send")
            }
        }
    }
}

@Composable
fun MessageBubble(message: Message) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            modifier = Modifier
                .widthIn(max = 300.dp),
            color = if (message.isUser) 
                MaterialTheme.colorScheme.primary 
            else 
                MaterialTheme.colorScheme.secondaryContainer,
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                message.text,
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = if (message.isUser)
                    MaterialTheme.colorScheme.onPrimary
                else
                    MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}

private fun startSpeechRecognition(
    context: Context,
    onResult: (String) -> Unit
) {
    val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
        putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
    }

    speechRecognizer.setRecognitionListener(object : android.speech.RecognitionListener {
        override fun onReadyForSpeech(params: android.os.Bundle?) {}
        override fun onBeginningOfSpeech() {}
        override fun onRmsChanged(rmsdB: Float) {}
        override fun onBufferReceived(buffer: ByteArray?) {}
        override fun onEndOfSpeech() {}
        override fun onError(error: Int) {}
        override fun onResults(results: android.os.Bundle?) {
            val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            if (!matches.isNullOrEmpty()) {
                onResult(matches[0])
            }
        }
        override fun onPartialResults(partialResults: android.os.Bundle?) {}
        override fun onEvent(eventType: Int, params: android.os.Bundle?) {}
    })

    speechRecognizer.startListening(intent)
}

import android.content.Intent
