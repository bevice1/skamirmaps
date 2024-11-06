
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
//        mavenLocal()
        mavenCentral()
        maven ("https://jitpack.io"  )
    }
}

rootProject.name = "skamirmaps"
include(":skamirmaps")
include(":skamirmaps-samples")
include(":androidApp")
