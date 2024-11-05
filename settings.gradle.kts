
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
    }
}

rootProject.name = "skamirmaps"
include(":skamirmaps")
include(":skamirmaps-samples")
include(":androidApp")