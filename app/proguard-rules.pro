# This file contains the log/output of running gradlew tasks.

-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile
-keep class com.aiagent.scanner.** { *; }
-keep class com.aiagent.scanner.utils.** { *; }
-keep class com.aiagent.scanner.ui.** { *; }
-keepclassmembers class * {
    *** *.*(..);
}

# OpenAI Client
-keep class com.aallam.openai.** { *; }
-keepclassmembers class com.aallam.openai.** { *; }

# Kotlin
-keep class kotlin.** { *; }
-keepclassmembers class kotlin.** { *; }

# Coroutines
-keep class kotlinx.coroutines.** { *; }
-keepclassmembers class kotlinx.coroutines.** { *; }

# Google ML Kit
-keep class com.google.mlkit.** { *; }
-keepclassmembers class com.google.mlkit.** { *; }

# AndroidX
-keep class androidx.** { *; }
-keepclassmembers class androidx.** { *; }

# Suppress warnings
-dontwarn okhttp3.**
-dontwarn **.R$*
-dontwarn kotlin.reflect.KClassifier
