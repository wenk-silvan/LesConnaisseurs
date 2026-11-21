This is a Kotlin Multiplatform project targeting Web only, use the webApp [js] launch configuration

# Getting started
- Create js build with: `./gradlew :webApp:jsBrowserDistribution`
- Login to firebase with: `firebase login`
- Publish to firebase with: `firebase deploy`

# Troubleshoot
- When the :kotlinStoreYarnLock Gradle task fails, run: `./gradlew kotlinUpgradeYarnLock`

