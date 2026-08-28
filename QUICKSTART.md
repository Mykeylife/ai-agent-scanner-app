# Quick Start Guide

## 🚀 Getting Started in 5 Minutes

### 1. Download and Install
```bash
# Clone the repository
git clone https://github.com/Mykeylife/ai-agent-scanner-app.git
cd ai-agent-scanner-app

# Build and install
./gradlew build
adb install app/build/outputs/apk/debug/app-debug.apk
```

### 2. Configure OpenAI API Key
- Get key from: https://platform.openai.com/account/api-keys
- Edit: `app/src/main/java/com/aiagent/scanner/utils/ConversationManager.kt`
- Replace: `private val apiKey = "sk-your-api-key-here"`

### 3. Grant Permissions
When app opens, grant:
- ✅ Camera
- ✅ Microphone  
- ✅ Bluetooth

### 4. Start Using!

---

## 📱 Feature Guide

### Text Scanner
**Scan printed text instantly**
1. Tap **Text Scanner** tab
2. Click **Open Camera**
3. Point at text → Auto-captures
4. Tap **Copy to Clipboard**
5. Paste anywhere!

**Pro Tips:**
- Good lighting = Better recognition
- Hold steady for 2 seconds
- Ensure text is in focus

### Ink Remover
**Remove handwriting from paper**
1. Tap **Ink Remover** tab
2. Click **Camera** or **Gallery**
3. Select/capture handwritten page
4. AI cleans the ink automatically
5. View extracted clean text
6. Save cleaned image

**Pro Tips:**
- Works best on light paper
- Blue/black ink supported
- High contrast = Better results

### Conversation Assistant
**Chat with AI via voice**
1. Tap **Conversation** tab
2. (Optional) Connect Bluetooth speaker
3. Type question or click **Mic** to speak
4. AI responds with voice
5. Conversation auto-continues

**Pro Tips:**
- Speak clearly and naturally
- Bluetooth = Better audio experience
- Context-aware responses
- Conversation history maintained

---

## 🔧 Troubleshooting

| Problem | Solution |
|---------|----------|
| Camera crashes | Grant camera permission, restart app |
| No text scanned | Better lighting, steady hand, focus clearly |
| Bluetooth not found | Enable Bluetooth, go to Settings, pair device |
| API errors | Check internet, verify API key, check quota |
| Audio not working | Enable microphone permission, check volume |

---

## 📞 Support

- **Issues?** → [GitHub Issues](https://github.com/Mykeylife/ai-agent-scanner-app/issues)
- **Questions?** → [GitHub Discussions](https://github.com/Mykeylife/ai-agent-scanner-app/discussions)
- **Contributing?** → See [CONTRIBUTING.md](CONTRIBUTING.md)

---

## 🎯 Next Steps

✅ Installation complete!  
✅ API key configured!  
✅ Permissions granted!  

**Now:**
1. Try scanning some text
2. Test ink remover on a handwritten note
3. Have a conversation with AI
4. Connect your Bluetooth speaker!

---

**Happy scanning! 📸**
