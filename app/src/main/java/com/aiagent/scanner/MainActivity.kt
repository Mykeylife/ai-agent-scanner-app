package com.aiagent.scanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aiagent.scanner.ui.screens.TextScannerScreen
import com.aiagent.scanner.ui.screens.InkRemoverScreen
import com.aiagent.scanner.ui.screens.ConversationScreen
import com.aiagent.scanner.ui.theme.AIAgentScannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AIAgentScannerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()
                }
            }
        }
    }
}

@Composable
fun MainApp() {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Text Scanner", "Ink Remover", "Conversation")

    Column(modifier = Modifier.fillMaxSize()) {
        when (selectedTab) {
            0 -> TextScannerScreen()
            1 -> InkRemoverScreen()
            2 -> ConversationScreen()
        }

        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .align(androidx.compose.ui.Alignment.CenterHorizontally)
        ) {
            tabs.forEachIndexed { index, title ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            when (index) {
                                0 -> androidx.compose.material.icons.Icons.Default.PhotoCamera
                                1 -> androidx.compose.material.icons.Icons.Default.AutoAwesome
                                else -> androidx.compose.material.icons.Icons.Default.Mic
                            },
                            contentDescription = title
                        )
                    },
                    label = { Text(title) },
                    selected = selectedTab == index,
                    onClick = { selectedTab = index }
                )
            }
        }
    }
}
