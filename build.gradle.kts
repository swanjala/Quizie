// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.apply {
        set("version_dagger", "2.48")
        set("version_retrofit", "2.9.0")
        set("version_moshi", "1.14.0")
        set("version_navigation", "2.7.7")
        set("version_moshi", "1.14.0")
    }
}
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}