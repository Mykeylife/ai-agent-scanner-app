# AI Agent Scanner App - Installation Guide

## Prerequisites
- Android SDK version 24 or higher
- Android Studio (latest version) OR Android Command Line Tools
- Java Development Kit (JDK) 11+
- Git

## Step-by-Step Installation

### Option 1: Using Android Studio (Recommended)

#### 1. Clone the Repository
```bash
git clone https://github.com/Mykeylife/ai-agent-scanner-app.git
cd ai-agent-scanner-app
```

#### 2. Open in Android Studio
- Launch Android Studio
- Select "Open an Existing Project"
- Navigate to the cloned folder and open it
- Android Studio will automatically download dependencies

#### 3. Configure OpenAI API Key
- Open `app/src/main/java/com/aiagent/scanner/utils/ConversationManager.kt`
- Replace `sk-your-api-key-here` with your actual OpenAI API key
- Get your key from: https://platform.openai.com/account/api-keys

#### 4. Build & Run
- Connect your Android device via USB (enable USB Debugging)
- Click "Run" or press `Shift + F10`
- Select your device and wait for installation

### Option 2: Using Command Line

#### 1. Clone Repository
```bash
git clone https://github.com/Mykeylife/ai-agent-scanner-app.git
cd ai-agent-scanner-app
```

#### 2. Add API Key
```bash
# Edit the file with your text editor
# Linux/Mac
nano app/src/main/java/com/aiagent/scanner/utils/ConversationManager.kt

# Windows
type app\src\main\java\com\aiagent\scanner\utils\ConversationManager.kt
```

#### 3. Build APK
```bash
# Linux/Mac
./gradlew build

# Windows
gradlew.bat build
```

#### 4. Install on Device
```bash
# Linux/Mac
adb install app/build/outputs/apk/debug/app-debug.apk

# Windows
adb install app\build\outputs\apk\debug\app-debug.apk
```

### Option 3: Direct APK Installation

If you already have a pre-built APK:

1. Transfer the APK to your Android device
2. Open File Manager on your device
3. Locate and tap the APK file
4. Tap "Install"
5. Grant requested permissions

## Post-Installation Setup

### Grant Permissions
When you first open the app, grant these permissions:
- ✅ Camera - Required for scanning
- ✅ Microphone - Required for voice input
- ✅ Location - Required for Bluetooth discovery
- ✅ Storage - Optional (for saving images)

### Connect Bluetooth Device (Optional)
1. Go to Settings > Bluetooth on your Android device
2. Pair with your speaker/headphones
3. Open AI Agent Scanner App
4. You'll see connection status in the Conversation tab

### Configure OpenAI API
The app requires an OpenAI API key for conversation features:

1. Create an account at https://openai.com
2. Go to https://platform.openai.com/account/api-keys
3. Create a new API key
4. Update the key in `ConversationManager.kt`
5. Ensure your OpenAI account has credits

## Troubleshooting

### Build Issues

**Error: Gradle download failed**
```bash
# Clear Gradle cache
rm -rf ~/.gradle  # Linux/Mac
rmdir %USERPROFILE%\.gradle  # Windows

# Try building again
./gradlew clean build
```

**Error: SDK not found**
- Open Android Studio
- Go to Tools > SDK Manager
- Install SDK Platform 34 (or your target version)
- Set `ANDROID_HOME` environment variable

### Runtime Issues

**App crashes on startup**
- Ensure all permissions are granted
- Check API key is set correctly
- Clear app data: Settings > Apps > AI Agent Scanner > Clear Data

**Camera not working**
- Grant camera permission
- Check if other apps are using camera
- Restart your device

**Bluetooth not connecting**
- Enable Bluetooth on device
- Forget device and re-pair in Settings
- Restart app and device

**API errors in conversation**
- Verify OpenAI API key is correct
- Check internet connection
- Ensure API key has credit/quota
- Check rate limits at https://platform.openai.com/usage/limits

## System Requirements

| Component | Minimum | Recommended |
|-----------|---------|-------------|
| Android Version | 7.0 (API 24) | 11.0+ (API 30+) |
| RAM | 2 GB | 4 GB+ |
| Storage | 200 MB free | 500 MB free |
| Network | 3G/4G | Wi-Fi preferred |

## Device Compatibility

✅ **Compatible Devices:**
- Samsung Galaxy (S10+, S20, S21, S22, etc.)
- Google Pixel (3, 4, 5, 6, 7, etc.)
- OnePlus (7T, 8, 9, 10, etc.)
- Xiaomi (Redmi, POCO, Mi series)
- Any device with Android 7.0+

❌ **Not Tested On:**
- Devices below Android 7.0
- Devices with less than 2GB RAM
- Devices without camera

## Performance Optimization

For best performance:

1. **Close background apps** - Frees up RAM
2. **Use Wi-Fi** - Faster API responses
3. **Keep device cool** - Prevents thermal throttling
4. **Update Android** - Latest security patches
5. **Clear cache regularly** - Free up storage

## Getting Help

If you encounter issues:

1. Check the [README.md](README.md) for detailed feature documentation
2. Review [Troubleshooting](#troubleshooting) section above
3. Search GitHub issues: https://github.com/Mykeylife/ai-agent-scanner-app/issues
4. Open a new issue with:
   - Device model and Android version
   - Error message/screenshot
   - Steps to reproduce
   - App version

## Next Steps

After successful installation:

1. **Test Text Scanner** - Scan any printed text
2. **Try Ink Remover** - Upload a handwritten note
3. **Start Conversation** - Chat with AI assistant
4. **Connect Bluetooth** - Pair your speaker/headphones

## Uninstallation

**Via Settings:**
1. Settings > Apps > AI Agent Scanner
2. Tap "Uninstall"
3. Confirm

**Via ADB:**
```bash
adb uninstall com.aiagent.scanner
```

---

**Enjoy using AI Agent Scanner! 🚀**
