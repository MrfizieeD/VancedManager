import Dependencies.accompanistVersion
import Dependencies.composeVersion
import Dependencies.koinVersion
import Dependencies.orchestraVersion
import Dependencies.retrofitVersion

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 30

    defaultConfig {
        minSdk = 21
        targetSdk = 30

        applicationId = "com.vanced.manager"

        versionCode = 3000
        versionName = "3.0.0 (Re@Composed)"

        vectorDrawables.useSupportLibrary = true

        buildConfigField("String[]", "MANAGER_LANGUAGES", "{$languages}")
    }

    lint {
        disable("MissingTranslation", "ExtraTranslation")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
    }

    packagingOptions {
        resources.excludes.add("META-INF/DEPENDENCIES")
        resources.excludes.add("META-INF/*.kotlin_module")
    }

    // To inline the bytecode built with JVM target 1.8 into
    // bytecode that is being built with JVM target 1.6. (e.g. navArgs)
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }

}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
}

val languages: String get() {
    val langs = arrayListOf("en", "bn_BD", "bn_IN", "pa_IN", "pa_PK", "pt_BR", "pt_PT", "zh_CN", "zh_TW")
    val exceptions = arrayOf("bn", "pa", "pt", "zh")

    File("$projectDir/src/main/res").listFiles()?.filter {
        val name = it.name
        name.startsWith("values") && !name.contains("v23") && !name.contains("night")
    }?.forEach { dir ->
        val dirname = dir.name.substringAfter("-").substringBefore("-")
        if (!exceptions.contains(dirname)) {
            langs.add(dirname)
        }
    }
    return langs.joinToString(", ") { "\"$it\"" }
}

dependencies {
    implementation(kotlin("reflect"))

    implementation("androidx.core:core-ktx:1.5.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.browser:browser:1.3.0")

    implementation("androidx.compose.compiler:compiler:$composeVersion")
    implementation("androidx.compose.foundation:foundation:$composeVersion")
    implementation("androidx.compose.material:material-icons-core:$composeVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.runtime:runtime-livedata:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.compose.ui:ui-util:$composeVersion")
    implementation("androidx.compose.ui:ui:$composeVersion")

    implementation("androidx.activity:activity-compose:1.3.0-beta02")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0-alpha02")
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha03")

    implementation("com.google.accompanist:accompanist-glide:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-swiperefresh:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-placeholder-material:$accompanistVersion")

    implementation("com.github.skydoves:orchestra-colorpicker:$orchestraVersion")

    implementation("androidx.datastore:datastore-preferences:1.0.0-beta02")

    implementation("io.insert-koin:koin-android:$koinVersion")
    implementation("io.insert-koin:koin-androidx-compose:$koinVersion")

    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    implementation("com.github.x1nto:apkhelper:1.1.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}
