# API Configuration Guide

## OpenAI API Setup

The Conversation Assistant feature requires an OpenAI API key.

### Step 1: Create OpenAI Account

1. Visit [https://openai.com/signup](https://openai.com/signup)
2. Sign up with email or Google account
3. Verify your email address
4. Complete the onboarding

### Step 2: Get API Key

1. Go to [https://platform.openai.com/account/api-keys](https://platform.openai.com/account/api-keys)
2. Click "+ Create new secret key"
3. Name your key (e.g., "Android App")
4. Copy the key (you won't see it again!)

### Step 3: Add to App

**File:** `app/src/main/java/com/aiagent/scanner/utils/ConversationManager.kt`

```kotlin
private val apiKey = "sk-your-api-key-here"
```

Replace `sk-your-api-key-here` with your actual API key.

### Step 4: Set Up Billing

1. Go to [https://platform.openai.com/account/billing/limits](https://platform.openai.com/account/billing/limits)
2. Set up payment method (required for API access)
3. Set usage limits (recommended)
4. Monitor usage at [https://platform.openai.com/usage/overview](https://platform.openai.com/usage/overview)

## API Usage & Costs

### Pricing (as of 2026)

| Model | Input | Output |
|-------|-------|--------|
| GPT-3.5 Turbo | $0.50/1M tokens | $1.50/1M tokens |
| GPT-4 | $30/1M tokens | $60/1M tokens |

### Usage Optimization

**Reduce costs:**
1. Use GPT-3.5 Turbo (cheaper)
2. Limit conversation history
3. Set reasonable context windows
4. Monitor usage regularly

**Current implementation:**
```kotlin
// Limits conversation history to 10 messages (20 entries)
if (conversationHistory.size > 20) {
    conversationHistory.removeAt(0)
    conversationHistory.removeAt(0)
}
```

## Security Best Practices

### Protecting Your API Key

✅ **DO:**
- Store in secure config
- Rotate keys regularly (monthly)
- Use different keys for dev/prod
- Monitor usage for unusual activity
- Delete unused keys

❌ **DON'T:**
- Commit keys to Git
- Share keys via email/chat
- Use in frontend code without backend
- Hardcode in production builds
- Leave keys in public repositories

### Environment Variables (Advanced)

**Create `.env` file:**
```
OPENAI_API_KEY=sk-your-key
```

**Load in Gradle:**
```kotlin
// In build.gradle.kts
val openAiKey = System.getenv("OPENAI_API_KEY") ?: "default"
```

## Monitoring API Usage

### Dashboard

1. Go to [Usage Overview](https://platform.openai.com/usage/overview)
2. View total tokens used
3. Check cost breakdown
4. Review daily usage

### Setting Up Alerts

1. Go to [Billing Settings](https://platform.openai.com/account/billing/limits)
2. Set monthly budget
3. Set usage alerts
4. Configure email notifications

### Rate Limits

Default limits (free tier):
- Requests per minute: 60 RPM
- Tokens per minute: 250,000 TPM

Upgrade tier for higher limits.

## Troubleshooting

### "Invalid API Key" Error

```
Error: 401 Unauthorized
Reason: Invalid authentication credentials
```

**Solution:**
1. Verify key is correct
2. Check key hasn't expired
3. Ensure key starts with `sk-`
4. Generate new key if needed

### "Rate Limit Exceeded" Error

```
Error: 429 Too Many Requests
Reason: Rate limit exceeded
```

**Solution:**
1. Wait before retrying (exponential backoff)
2. Upgrade to higher tier
3. Reduce message frequency
4. Optimize token usage

### "Insufficient Quota" Error

```
Error: 429 Insufficient Quota
Reason: Account quota exceeded
```

**Solution:**
1. Check billing status
2. Add payment method
3. Wait for monthly quota reset
4. Contact OpenAI support

## Models Reference

### Available Models

**Latest (Recommended):**
- `gpt-4` - Most capable, most expensive
- `gpt-4-32k` - Extended context (32K tokens)
- `gpt-3.5-turbo` - Fast, cheap, good quality

**Current app uses:** `gpt-3.5-turbo` (cost-effective)

### Changing Models

**File:** `ConversationManager.kt`

```kotlin
val request = ChatCompletionRequest(
    model = "gpt-3.5-turbo",  // Change here
    messages = conversationHistory
)
```

## Integration Testing

### Test API Connection

```kotlin
suspend fun testConnection() {
    try {
        val response = openAI.chatCompletion(
            ChatCompletionRequest(
                model = "gpt-3.5-turbo",
                messages = listOf(
                    ChatMessage(role = ChatRole.User, content = "Hi")
                )
            )
        )
        println("✓ API Working")
    } catch (e: Exception) {
        println("✗ API Error: ${e.message}")
    }
}
```

## Additional Resources

- [OpenAI API Docs](https://platform.openai.com/docs/api-reference)
- [API Pricing](https://openai.com/pricing)
- [API Status](https://status.openai.com/)
- [Support](https://platform.openai.com/support)

---

**Last Updated:** August 28, 2026
