package com.aiagent.scanner.utils

import android.content.Context
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.client.OpenAI
import kotlinx.coroutines.runBlocking

class ConversationManager(private val context: Context) {
    private val conversationHistory = mutableListOf<ChatMessage>()
    
    // Replace with your actual OpenAI API key
    private val apiKey = "sk-your-api-key-here"
    private val openAI = OpenAI(apiKey)

    suspend fun getResponse(userMessage: String): String {
        return try {
            conversationHistory.add(ChatMessage(role = ChatRole.User, content = userMessage))

            val request = ChatCompletionRequest(
                model = "gpt-3.5-turbo",
                messages = conversationHistory
            )

            val response = openAI.chatCompletion(request)
            val assistantMessage = response.choices.firstOrNull()?.message?.content ?: "I couldn't generate a response."

            conversationHistory.add(ChatMessage(role = ChatRole.Assistant, content = assistantMessage))

            // Keep conversation history limited to last 10 messages
            if (conversationHistory.size > 20) {
                conversationHistory.removeAt(0)
                conversationHistory.removeAt(0)
            }

            assistantMessage
        } catch (e: Exception) {
            e.printStackTrace()
            "Sorry, I encountered an error. Please try again. Error: ${e.message}"
        }
    }
}
