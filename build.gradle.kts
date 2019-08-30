import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "pathfinder"
version = "2.0"

plugins {
    kotlin("jvm") version "1.3.50"
    application
}

repositories {
    jcenter()
}

application {
    mainClassName = "pathfinder.weatherBot.Interface"
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
}

sourceSets {
    main {
        java.srcDir("/src/main/kotlin/pathfinder/weatherBot")
    }
}