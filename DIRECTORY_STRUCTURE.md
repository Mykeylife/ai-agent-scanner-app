# Directory Structure

```
ai-agent-scanner-app/
│
├── app/                                    # Main app module
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/aiagent/scanner/
│   │   │   │   ├── MainActivity.kt         # App entry point & navigation
│   │   │   │   │
│   │   │   │   ├── ui/                     # User Interface
│   │   │   │   │   ├── screens/
│   │   │   │   │   │   ├── TextScannerScreen.kt     # Real-time text OCR UI
│   │   │   │   │   │   ├── InkRemoverScreen.kt      # AI ink removal UI
│   │   │   │   │   │   └── ConversationScreen.kt    # Voice chat UI
│   │   │   │   │   │
│   │   │   │   │   └── theme/
│   │   │   │   │       ├── Theme.kt                 # Material Design 3 colors
│   │   │   │   │       └── Type.kt                  # Typography styles
│   │   │   │   │
│   │   │   │   └── utils/                 # Business Logic & Utilities
│   │   │   │       ├── InkRemovalProcessor.kt    # AI bilateral filter
│   │   │   │       ├── ConversationManager.kt    # OpenAI integration
│   │   │   │       └── BluetoothAudioManager.kt  # Bluetooth TTS/STT
│   │   │   │
│   │   ���   └── res/
│   │   │       ├── values/
│   │   │       │   ├── strings.xml        # UI text strings
│   │   │       │   ├── colors.xml         # Color definitions
│   │   │       │   ├── bools.xml          # Boolean resources
│   │   │       │   └── themes.xml         # Theme styles
│   │   │       ├── drawable/              # App icons & images
│   │   │       └── xml/
│   │   │           └── preferences.xml    # Settings schema
│   │   │
│   │   └── AndroidManifest.xml            # App permissions & config
│   │
│   ├── build.gradle.kts                   # App module dependencies
│   └── proguard-rules.pro                 # Code obfuscation rules
│
├── gradle/
│   └── wrapper/
│       └── gradle-wrapper.properties       # Gradle version config
│
├── build.gradle.kts                       # Project-level config
├── settings.gradle.kts                    # Gradle settings
├── gradle.properties                      # Build properties
├── gradlew                                # Gradle wrapper (Linux/Mac)
├── gradlew.bat                            # Gradle wrapper (Windows)
│
├── ��� Documentation Files
│   ├── README.md                          # Main project overview
│   ├── PROJECT_COMPLETION.md              # This file - complete setup guide
│   ├── INSTALLATION.md                    # Detailed installation steps
│   ├── QUICKSTART.md                      # 5-minute quick start
│   ├── API_SETUP.md                       # OpenAI API configuration
│   ├── CONTRIBUTING.md                    # Contribution guidelines
│   ├── CHANGELOG.md                       # Version history
│   ├── SECURITY.md                        # Security & privacy info
│   ├── GRADLE_GUIDE.md                    # Build system guide
│   ├── LICENSE                            # MIT License
│   └── DIRECTORY_STRUCTURE.md             # This file
│
└── .gitignore                             # Git ignore rules
```

## File Descriptions

### Source Code Files

#### `MainActivity.kt`
- Entry point of the application
- Manages tab navigation between 3 screens
- Initializes Compose UI framework
- Handles Android lifecycle

#### Screen Files (UI Layer)

**TextScannerScreen.kt**
- Real-time camera preview
- Google ML Kit OCR integration
- Text extraction and display
- Copy-to-clipboard functionality

**InkRemoverScreen.kt**
- Camera/gallery image capture
- AI ink removal processing
- Bilateral filter algorithm
- Auto text extraction

**ConversationScreen.kt**
- Message UI with bubbles
- Voice recognition input
- OpenAI API integration
- Text-to-speech output
- Bluetooth audio routing

#### Utility Files (Business Logic)

**InkRemovalProcessor.kt**
- Custom bilateral filter implementation
- Saturation-based ink detection
- Color space conversion
- Smoothing algorithms

**ConversationManager.kt**
- OpenAI API client
- Chat completion requests
- Conversation history management
- Token optimization

**BluetoothAudioManager.kt**
- Bluetooth device management
- Text-to-speech synthesis
- Audio focus handling
- Connection state tracking

#### Theme Files

**Theme.kt**
- Material Design 3 colors
- Light/dark color schemes
- Theme composition

**Type.kt**
- Typography definitions
- Font sizes and weights
- Text styles

### Configuration Files

#### `build.gradle.kts` (App Module)
- Android SDK versions
- App dependencies
- Build variants
- ProGuard configuration

#### `settings.gradle.kts`
- Project structure
- Repository definitions
- Module inclusion

#### `gradle.properties`
- JVM memory settings
- Build optimizations
- Gradle parallel builds
- SDK version defaults

#### `AndroidManifest.xml`
- App permissions
- Activities declaration
- Intent filters
- Feature requirements

### Resource Files

#### `strings.xml`
UI text and labels

#### `colors.xml`
Color palette definitions

#### `themes.xml`
App theme styling

#### `preferences.xml`
Settings schema

### Build Files

#### `gradlew` / `gradlew.bat`
Gradle wrapper scripts for building

#### `gradle-wrapper.properties`
Gradle version specification

#### `proguard-rules.pro`
Code obfuscation and shrinking rules

## Key Directories

### `app/src/main/java/com/aiagent/scanner/`
**Location of all Kotlin source code**
- Well-organized package structure
- Clear separation of concerns
- UI, utilities, and theme modules

### `app/src/main/res/`
**Android resources**
- Strings, colors, styles
- Drawable assets
- XML configurations

### `gradle/wrapper/`
**Gradle wrapper configuration**
- Ensures consistent Gradle version
- Simplifies builds across machines

## Build Output

When you build the app:

```
.gradle/          # Gradle cache (auto-generated)
build/            # Build outputs
├── outputs/
│   ├── apk/       # APK files
│   │   ├── debug/ # Debug APK (for testing)
│   │   └── release/ # Release APK (optimized)
│   └── bundle/    # Android App Bundles
└── ...
```

## File Organization Principles

✅ **Clean Architecture**
- Separation of concerns
- UI layer (screens)
- Business logic (utils)
- Presentation layer (theme)

✅ **Package Organization**
- By feature (Text Scanner, Ink Remover, Conversation)
- By layer (UI, Utils, Theme)
- Clear package naming

✅ **Configuration Management**
- Centralized Gradle setup
- Resource definitions
- Manifest declaration

✅ **Documentation**
- Comprehensive guides
- Security policies
- Contribution guidelines
- API setup instructions

## How to Navigate

### To modify UI:
1. Look in `ui/screens/` for the feature
2. Find corresponding `.kt` file
3. Edit Compose functions

### To change business logic:
1. Look in `utils/` folder
2. Find the manager class
3. Update methods

### To update styling:
1. Modify `ui/theme/Theme.kt`
2. Update color schemes
3. Adjust typography in `Type.kt`

### To add permissions:
1. Edit `AndroidManifest.xml`
2. Add `<uses-permission>` tag
3. Request at runtime

### To add dependencies:
1. Open `app/build.gradle.kts`
2. Add to `dependencies` block
3. Sync Gradle

---

**This structure ensures maintainability, scalability, and clean code organization!**
