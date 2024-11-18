group = "pathfinder"
version = "2.0"

plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.jpa") version "2.0.21"
    kotlin("plugin.spring") version "2.0.21"
    id("org.springframework.boot") version "3.3.5"
    id("io.spring.dependency-management") version "1.1.6"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
    maven { url = uri("https://m2.chew.pro/releases") }
    maven {
        url = uri("https://maven.pkg.github.com/TheMather1/dice-syntax")
        credentials {
            username = "token"
            password = System.getenv("GITHUB_TOKEN")
        }
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("com.h2database", "h2", "2.3.232")
    implementation("net.dv8tion", "JDA", "5.2.1")
    implementation("no.mather.ttrpg", "dice-syntax", "0.2.0")
    implementation("org.postgresql", "postgresql", "42.7.4")
    implementation("org.springframework.boot", "spring-boot-starter-actuator")
    implementation("org.springframework.boot", "spring-boot-starter-data-jpa")
    implementation("org.springframework.boot", "spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot", "spring-boot-starter-web")
    implementation("org.springframework.boot", "spring-boot-starter-thymeleaf")
    implementation("pw.chew", "jda-chewtils", "2.0")
}

tasks {
    test {
        useJUnitPlatform()
    }
}
