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
    testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
    compile("net.dv8tion:JDA:4.0.0_46")
}

sourceSets {
    main {
        java.srcDir("/src/main/kotlin")
    }
    test {
        java.srcDir("/src/test/kotlin")
    }
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs += "-Xuse-experimental=kotlin.Experimental"
        }
    }
    test {
        useJUnitPlatform()
    }
}