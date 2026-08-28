# Security Policy

## Reporting Security Vulnerabilities

If you discover a security vulnerability in the AI Agent Scanner App, please email the maintainers privately instead of using the issue tracker.

### How to Report

1. **Do NOT** create a public GitHub issue
2. Email the security concern to: **[security@aiagent-scanner.dev]**
3. Include:
   - Description of the vulnerability
   - Steps to reproduce
   - Potential impact
   - Suggested fix (if any)

### What to Expect

- Acknowledgment within 48 hours
- Regular updates on progress
- Credit in security advisories (if desired)
- Coordinated disclosure timeline

## Security Considerations

### API Keys
- **NEVER** commit API keys to version control
- Use environment variables for sensitive data
- Rotate keys regularly
- Monitor usage at https://platform.openai.com/usage/billing

### Bluetooth Security
- Only pair with trusted devices
- Ensure your Android version is updated
- Disable Bluetooth when not in use
- Review connected devices regularly

### Camera Permissions
- App requires camera for scanning features
- Images are processed locally on your device
- No images are uploaded without explicit consent
- Clear app cache regularly

### Microphone & Audio
- Microphone is used only for voice recognition
- Audio is not recorded or stored
- Ensure location permission is for Bluetooth only
- Review microphone usage in Settings > Apps

### Network Security
- All API calls use HTTPS/TLS encryption
- OpenAI API communication is encrypted
- Conversation data is sent to OpenAI servers
- Review OpenAI Privacy Policy: https://openai.com/policies/privacy-policy

### Data Privacy

**What is stored locally:**
- Scanned text (until cleared)
- Processed images (until deleted)
- Conversation history (until cleared)
- App settings and preferences

**What is sent online:**
- Conversation messages → OpenAI API
- Error logs (optional)
- Analytics (none currently)

**What is NOT collected:**
- Camera images (unless for OCR processing)
- Audio recordings
- Location data
- Device identifiers
- User behavior tracking

### Permissions Justification

| Permission | Purpose | Required |
|-----------|---------|----------|
| CAMERA | Photo scanning | Yes |
| RECORD_AUDIO | Voice input | Yes |
| INTERNET | API calls | Yes |
| BLUETOOTH | Audio devices | No |
| ACCESS_FINE_LOCATION | Bluetooth discovery | No |
| READ/WRITE_STORAGE | Save images | No |

## Security Best Practices

### For Users

1. **Keep Android Updated**
   - Security patches are crucial
   - Enable auto-updates in Play Store

2. **Use Strong Passwords**
   - OpenAI account security
   - Android device lock

3. **Monitor API Usage**
   - Check OpenAI dashboard monthly
   - Set usage alerts
   - Review API keys regularly

4. **Clear Sensitive Data**
   - Clear app cache regularly
   - Delete saved images after use
   - Clear conversation history as needed

5. **Review Permissions**
   - Regularly check granted permissions
   - Disable unnecessary permissions
   - Monitor Bluetooth connections

### For Developers

1. **Code Security**
   - No hardcoded secrets
   - Input validation
   - Error handling without exposing info
   - Regular dependency updates

2. **Dependency Management**
   ```bash
   ./gradlew dependencyUpdates
   ./gradlew vulnerabilityCheck
   ```

3. **Code Review**
   - All PRs require review
   - Security-focused reviews
   - Follow OWASP guidelines

4. **Testing**
   ```bash
   ./gradlew test
   ./gradlew lint
   ```

## Security Updates

We release security updates as needed:

- **Critical:** Within 24 hours
- **High:** Within 7 days
- **Medium:** Next scheduled release
- **Low:** Future release

Follow releases at: https://github.com/Mykeylife/ai-agent-scanner-app/releases

## Compliance

- ✅ Android Security Guidelines
- ✅ OWASP Mobile Security
- ✅ Google Play Security Policies
- ✅ Privacy Best Practices

## Support

- 🔒 Security Issues: Email (see above)
- 🐛 Bug Reports: GitHub Issues
- 💬 Questions: GitHub Discussions

---

**Last Updated:** August 28, 2026
