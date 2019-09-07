group = "pathfinder"
version = "2.0"

plugins {
    kotlin("jvm") version "1.3.50"
    application
}

repositories {
    jcenter()
}

val sourceCompatibility = JavaVersion.VERSION_1_8

application {
    mainClassName = "pathfinder.weatherBot.Interface"
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    compile("net.dv8tion:JDA:4.0.0_46")
}

sourceSets {
    main {
        java.srcDir("/src/main/kotlin/pathfinder/weatherBot")
    }
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}