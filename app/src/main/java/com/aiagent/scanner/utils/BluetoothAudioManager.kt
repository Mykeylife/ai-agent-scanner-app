package com.aiagent.scanner.utils

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.AudioFocusRequest
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import java.util.Locale

class BluetoothAudioManager(private val context: Context) {
    private var textToSpeech: TextToSpeech? = null
    private val bluetoothAdapter = (context.getSystemService(Context.BLUETOOTH_SERVICE) as? BluetoothManager)?.adapter
    private val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    private var onConnectionStateChanged: ((Boolean) -> Unit)? = null

    init {
        initTextToSpeech()
        checkBluetoothConnection()
    }

    private fun initTextToSpeech() {
        textToSpeech = TextToSpeech(context, OnInitListener { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech?.language = Locale.ENGLISH
                configureAudioForBluetooth()
            }
        })
    }

    private fun configureAudioForBluetooth() {
        try {
            // Request audio focus
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build()

            val focusRequest = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
                .setAudioAttributes(audioAttributes)
                .setOnAudioFocusChangeListener { focusChange ->
                    when (focusChange) {
                        AudioManager.AUDIOFOCUS_LOSS -> {
                            textToSpeech?.stop()
                        }
                    }
                }
                .build()

            audioManager.requestAudioFocus(focusRequest)

            // Route to Bluetooth speaker if connected
            if (audioManager.isBluetoothScoAvailableOffCall) {
                audioManager.startBluetoothSco()
                audioManager.isBluetoothScoOn = true
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun speak(text: String, onDone: () -> Unit = {}) {
        if (textToSpeech == null || text.isEmpty()) {
            onDone()
            return
        }

        try {
            configureAudioForBluetooth()
            textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null)
            onDone()
        } catch (e: Exception) {
            e.printStackTrace()
            onDone()
        }
    }

    private fun checkBluetoothConnection() {
        try {
            val isConnected = bluetoothAdapter?.bondedDevices?.isNotEmpty() ?: false
            onConnectionStateChanged?.invoke(isConnected)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setOnConnectionStateChanged(callback: (Boolean) -> Unit) {
        onConnectionStateChanged = callback
    }

    fun shutdown() {
        textToSpeech?.shutdown()
        audioManager.stopBluetoothSco()
        audioManager.isBluetoothScoOn = false
    }
}
