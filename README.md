# AI Agent Scanner App

A powerful Android application that combines AI-powered text scanning, intelligent ink removal, and conversational AI with Bluetooth audio support.

## Features

### 1. **Text Scanner** 📷
- Real-time camera-based text extraction
- Automatic OCR using Google ML Kit
- One-tap copy to clipboard
- Works offline with on-device ML models

### 2. **Ink Remover** ✨
- AI-powered ink removal using bilateral filtering
- Acts as "correction fluid" to clean handwritten ink from paper scans
- Extracts cleaned text automatically
- Save cleaned images for later use

### 3. **Conversation Assistant** 🎙️
- Voice-based AI conversations using OpenAI GPT API
- Real-time speech recognition
- Natural language responses with text-to-speech
- Bluetooth headphone/speaker integration
- Conversation history management

### 4. **Bluetooth Integration** 🎧
- Seamless connection to Bluetooth speakers/headphones
- Automatic audio routing
- Real-time connection status display

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Camera**: AndroidX Camera Library
- **Text Recognition**: Google ML Kit OCR
- **AI/Conversation**: OpenAI GPT API
- **Speech**: Android Speech Recognition + Text-to-Speech
- **Image Processing**: Custom bilateral filtering algorithm
- **Bluetooth**: Android Bluetooth API

## Requirements

- Android SDK 24 (Android 7.0) or higher
- Target SDK 34 (Android 14)
- 2GB RAM minimum
- Camera permission
- Microphone permission
- Bluetooth permission (for audio devices)

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/Mykeylife/ai-agent-scanner-app.git
cd ai-agent-scanner-app
```

### 2. Add Your API Key

Edit `app/src/main/java/com/aiagent/scanner/utils/ConversationManager.kt`:
```kotlin
private val apiKey = "sk-your-openai-api-key-here"
```

Get your OpenAI API key from [platform.openai.com](https://platform.openai.com/account/api-keys)

### 3. Build the APK

```bash
# Using Gradle
./gradlew build

# Or use Android Studio
# File > Build > Build APK(s)
```

### 4. Install on Android Device

```bash
# Connect your device via USB (enable USB debugging)
adb install app/build/outputs/apk/debug/app-debug.apk
```

Or install the APK directly on your device via file manager.

## Usage

### Text Scanner
1. Open the app and go to **Text Scanner** tab
2. Click **Open Camera**
3. Point at any text document
4. Wait for automatic capture and text extraction
5. Click **Copy to Clipboard** to save the text

### Ink Remover
1. Go to **Ink Remover** tab
2. Click **Camera** to capture or **Gallery** to select existing image
3. AI will automatically remove ink and brighten the background
4. View extracted text from cleaned image
5. Click **Save Cleaned Image** to store the result

### Conversation Assistant
1. Go to **Conversation** tab
2. Connect Bluetooth headphones/speaker (optional)
3. Type your question or click **Mic** to speak
4. AI responds with voice output through your device speaker or Bluetooth device
5. Conversation history is maintained for context

## Offline Mode

- **Text Scanner**: Fully offline (uses ML Kit on-device models)
- **Ink Remover**: Fully offline (uses local image processing)
- **Conversation**: Requires internet for API calls (can work with cached responses)

## Permissions

The app requests the following permissions:
- `CAMERA` - For scanning documents
- `RECORD_AUDIO` - For voice input
- `INTERNET` - For AI API calls
- `BLUETOOTH` - For audio device connectivity
- `ACCESS_FINE_LOCATION` - For Bluetooth device discovery

## Architecture

```
app/
├── src/main/
│   ├── java/com/aiagent/scanner/
│   │   ├── MainActivity.kt (App entry point)
│   │   ├── ui/
│   │   │   ├── screens/
│   │   │   │   ├── TextScannerScreen.kt
│   │   │   │   ├── InkRemoverScreen.kt
│   │   │   │   └── ConversationScreen.kt
│   │   │   └── theme/
│   │   │       ├── Theme.kt
│   │   │       └── Type.kt
│   │   └── utils/
│   │       ├── InkRemovalProcessor.kt (AI ink removal)
│   │       ├── ConversationManager.kt (OpenAI integration)
│   │       └── BluetoothAudioManager.kt (Bluetooth handling)
│   └── res/
│       └── values/strings.xml
├── build.gradle.kts (App dependencies)
└── AndroidManifest.xml (Permissions & configuration)
```

## Dependencies

- AndroidX (Core, AppCompat, Compose, Camera, Room)
- Google ML Kit (Text Recognition)
- OpenAI Client Library
- Kotlin Coroutines
- Ktor HTTP Client

## Performance Tips

1. **Bluetooth Connection**: Ensure Bluetooth is enabled on both devices
2. **API Rate Limits**: Monitor OpenAI API usage to avoid rate limits
3. **Memory**: Close other apps for better performance during scanning
4. **Network**: Use Wi-Fi for faster API responses

## Troubleshooting

### Camera Not Working
- Check camera permission in Settings > Apps > AI Agent Scanner
- Restart the app
- Restart your device

### Speech Recognition Not Working
- Ensure microphone permission is granted
- Check internet connection for online recognition
- Speak clearly and wait for confirmation

### Bluetooth Not Connecting
- Enable Bluetooth on both devices
- Forget the device and re-pair
- Restart Bluetooth on your device

### API Errors
- Verify OpenAI API key is correct
- Check internet connection
- Ensure API key has sufficient credits

## Future Enhancements

- [ ] Support for multiple languages
- [ ] Handwriting recognition with AI correction
- [ ] Document scanning with auto-enhancement
- [ ] Multi-page document support
- [ ] Cloud backup and sync
- [ ] Dark mode support
- [ ] Offline conversation with local LLM
- [ ] PDF export functionality

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

MIT License - See LICENSE file for details

## Support

For issues and questions, please open an issue on GitHub.

---

**Made with ❤️ by Mykeylife**
