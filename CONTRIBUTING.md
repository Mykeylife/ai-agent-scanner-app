# Contributing to AI Agent Scanner App

## Welcome! 👋

Thank you for your interest in contributing to the AI Agent Scanner App. This document provides guidelines and instructions for contributing.

## Code of Conduct

- Be respectful and inclusive
- Provide constructive feedback
- Help others learn and grow
- Report issues responsibly

## How to Contribute

### 1. Reporting Bugs 🐛

**Before submitting a bug report:**
- Check existing issues to avoid duplicates
- Try reproducing the issue on the latest version
- Gather as much information as possible

**Submit bug report with:**
- Device model and Android version
- App version
- Steps to reproduce
- Expected behavior
- Actual behavior
- Screenshots/videos (if applicable)
- Error logs/stack traces

### 2. Suggesting Enhancements 💡

**Good enhancement suggestions include:**
- Clear use case and benefits
- How it fits with existing features
- Possible implementation approach
- Related research or examples

### 3. Code Contributions 🔧

#### Getting Started

1. **Fork the repository**
   ```bash
   git clone https://github.com/YOUR_USERNAME/ai-agent-scanner-app.git
   cd ai-agent-scanner-app
   ```

2. **Create a feature branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

3. **Make your changes**
   - Follow Kotlin coding standards
   - Write clear, commented code
   - Keep commits atomic and well-documented

4. **Test thoroughly**
   ```bash
   ./gradlew test
   ./gradlew build
   ```

5. **Push to your fork**
   ```bash
   git push origin feature/your-feature-name
   ```

6. **Create a Pull Request**
   - Describe what you changed and why
   - Reference any related issues
   - Include screenshots for UI changes
   - Ensure all tests pass

## Development Guidelines

### Kotlin Style Guide

- Follow [Google's Kotlin Style Guide](https://developer.android.com/kotlin/style-guide)
- Use meaningful variable names
- Limit line length to 100 characters
- Use `val` over `var` when possible
- Add KDoc comments for public functions

### Commit Messages

```
[TYPE] Brief description (max 50 chars)

More detailed explanation of changes (max 72 chars per line)

Fixes #ISSUE_NUMBER
```

**Types:**
- `feat:` New feature
- `fix:` Bug fix
- `docs:` Documentation
- `style:` Formatting
- `refactor:` Code restructuring
- `perf:` Performance improvement
- `test:` Test additions/fixes
- `chore:` Dependencies/build

### Branch Naming

```
feature/description          - New features
fixture/description          - Bug fixes
docs/description             - Documentation
refactor/description         - Refactoring
```

## Pull Request Process

1. **Update README** if adding new features
2. **Add/update tests** for your changes
3. **Ensure code compiles** without warnings
4. **Run full test suite** locally
5. **Keep PR focused** - one feature per PR
6. **Request review** from maintainers
7. **Address feedback** promptly
8. **Squash commits** before merge if requested

## Testing

### Unit Tests
```bash
./gradlew test
```

### Integration Tests
```bash
./gradlew connectedAndroidTest
```

### Build Verification
```bash
./gradlew build
./gradlew lint
```

## Project Structure

```
app/src/main/
├── java/com/aiagent/scanner/
│   ├── MainActivity.kt                    # Entry point
│   ├── ui/
│   │   ├── screens/                       # UI screens
│   │   │   ├── TextScannerScreen.kt
│   │   │   ├── InkRemoverScreen.kt
│   │   │   └── ConversationScreen.kt
│   │   └── theme/                         # UI theme
│   └── utils/                             # Utilities
│       ├── InkRemovalProcessor.kt         # Image processing
│       ├── ConversationManager.kt         # AI integration
│       └── BluetoothAudioManager.kt       # Audio handling
├── res/
│   ├── values/                            # Resources
│   └── drawable/                          # Assets
└── AndroidManifest.xml                    # Manifest
```

## Areas for Contribution

### High Priority 🔴
- Offline conversation support
- Performance optimization
- Additional language support
- Improved error handling

### Medium Priority 🟡
- Enhanced UI/UX
- Dark mode support
- PDF export
- Cloud sync

### Low Priority 🟢
- Documentation improvements
- Example projects
- Blog posts/tutorials

## Technology Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Database:** Room
- **Networking:** OkHttp + Retrofit
- **Image Processing:** OpenCV
- **AI/ML:** Google ML Kit + OpenAI API
- **Bluetooth:** Android BLE APIs

## Getting Help

- **Documentation:** Check README.md and INSTALLATION.md
- **Issues:** Search existing issues first
- **Discussions:** Join GitHub discussions
- **Email:** Contact maintainers

## Licensing

By contributing, you agree that your contributions will be licensed under the MIT License.

## Recognition

Contributors will be recognized in:
- CONTRIBUTORS.md file
- Release notes
- GitHub contributors page

## Questions?

Feel free to open an issue or discussion if you have questions about contributing.

---

**Happy coding! 🚀**
