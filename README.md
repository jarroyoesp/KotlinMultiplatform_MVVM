# Kotlin-Multiplatform MVVM (Android & iOS)
![kotlin-version](https://img.shields.io/badge/kotlin-1.5.30-orange)
<a target="_blank" href="https://androidweekly.net/issues/issue-397"><img src="https://androidweekly.net/issues/issue-397/badge"></a>

Example of application using Kotlin Multiplatform and MVVM pattern for both platforms (Android & iOS). To achieve it the libraries used are:

- [moko-mvvm](https://github.com/icerockdev/moko-mvvm): This is a Kotlin Multiplatform library that provides architecture components of Model-View-ViewModel for UI applications. Components are lifecycle-aware on Android.
- [KTOR](https://github.com/ktorio/ktor): to make HTTP requests
- [Serialization](https://github.com/Kotlin/kotlinx.serialization): to De/Serializing JSON 
- [Kodein-DI](https://github.com/Kodein-Framework/Kodein-DI): Dependency injector
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines): Library support for Kotlin coroutines with multiplatform support
- [SQLDelight](https://github.com/cashapp/sqldelight): SQLDelight generates typesafe kotlin APIs from your SQL statements.

More detail in this post on ProAndroidDev: [here](https://proandroiddev.com/kotlin-multiplatform-mvvm-clean-architecture-f20b99f90b95)

### Unit Testing SharedCode

- Added unitTest for GitHubRepository

<img src="https://github.com/jarroyoesp/KotlinMultiplatform_MVVM/blob/master/images/KMP_MVVM_Schema.png">
