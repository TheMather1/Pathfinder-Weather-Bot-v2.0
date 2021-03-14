group = "pathfinder"
version = "2.0"

plugins {
    kotlin("jvm") version "1.4.30"
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("net.dv8tion", "JDA", "4.2.0_236")
    implementation("org.slf4j", "slf4j-jdk14", "1.7.30")
    implementation("club.minnced", "jda-reactor", "1.2.0")
    testImplementation("org.junit.jupiter", "junit-jupiter", "5.5.2")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    test {
        useJUnitPlatform()
    }
}