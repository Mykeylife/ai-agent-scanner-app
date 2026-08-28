# Gradle Build Scripts Configuration

## Project Structure Overview

This project uses Gradle 8.1 for building and dependency management.

### Build Configuration

**File:** `build.gradle.kts` (Top-level)
- Defines plugins
- Sets up version management
- Configures repositories (Google, Maven Central, JitPack)

**File:** `app/build.gradle.kts` (Module-level)
- Application configuration
- Compilation settings
- Dependencies specification
- Build variants (debug/release)

### Gradle Properties

**File:** `gradle.properties`
- JVM memory settings
- Parallel build configuration
- Android SDK versions
- AndroidX settings

## Building the Project

### Prerequisites

```bash
# Check Gradle wrapper is executable
chmod +x gradlew  # Linux/Mac
```

### Build Commands

```bash
# Clean build
./gradlew clean build

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Build app bundle (for Google Play)
./gradlew bundleRelease

# Run tests
./gradlew test

# Run lint checks
./gradlew lint

# Build and run on connected device
./gradlew installDebug
```

### Gradle Tasks

```bash
# List all available tasks
./gradlew tasks

# View dependency tree
./gradlew dependencies

# Check for dependency updates
./gradlew dependencyUpdates

# Display build configuration
./gradlew -v
```

## Gradle Wrapper

The Gradle Wrapper ensures the correct Gradle version is used:

- **Linux/Mac:** Use `./gradlew`
- **Windows:** Use `gradlew.bat`
- **Version:** Gradle 8.1 (specified in `gradle/wrapper/gradle-wrapper.properties`)

## Dependency Management

### Adding Dependencies

Edit `app/build.gradle.kts`:

```kotlin
dependencies {
    implementation("group:artifact:version")
}
```

### Version Updates

```bash
# Check for outdated dependencies
./gradlew dependencyUpdates

# Update a specific dependency
# Edit build.gradle.kts and rebuild
./gradlew clean build
```

## ProGuard Configuration

**File:** `app/proguard-rules.pro`

Configures code obfuscation for release builds:
- Keeps app classes unobfuscated (for debugging)
- Preserves external library classes
- Handles Kotlin reflection
- Maintains AndroidX compatibility

## Build Optimization

### Performance Tuning

```bash
# Enable parallel builds (already configured)
# Set in gradle.properties: org.gradle.parallel=true

# Increase max workers
# Set in gradle.properties: org.gradle.workers.max=4

# Increase JVM memory
# Set in gradle.properties: org.gradle.jvmargs=-Xmx2048m
```

### Build Cache

```bash
# Enable build cache
./gradlew build --build-cache

# Clean build cache
./gradlew cleanBuildCache
```

## Continuous Integration

### GitHub Actions Example

```yaml
name: Build
on: [push, pull_request]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-java@v3
        with:
          java-version: '11'
      - run: ./gradlew build
```

## Troubleshooting

### Common Issues

**Gradle sync failed**
```bash
./gradlew clean
rm -rf .gradle
./gradlew build
```

**Out of memory**
```bash
# Increase JVM memory in gradle.properties
org.gradle.jvmargs=-Xmx4096m
```

**Dependency resolution failed**
```bash
./gradlew build --refresh-dependencies
```

**Module not found**
```bash
# Clear local Maven cache
rm -rf ~/.m2/repository
./gradlew build
```

## Documentation

- [Gradle Documentation](https://docs.gradle.org/)
- [Android Gradle Plugin](https://developer.android.com/studio/build)
- [Gradle Best Practices](https://docs.gradle.org/current/userguide/best_practices.html)

---

**Last Updated:** August 28, 2026
