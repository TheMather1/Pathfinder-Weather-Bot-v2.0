group = "pathfinder"
version = "2.0"

plugins {
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.spring") version "1.6.0"
    id("org.springframework.boot") version "2.6.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("javax.servlet", "jstl", "1.2")
    implementation("net.dv8tion", "JDA", "5.0.0-alpha.3")
    implementation("org.mapdb", "mapdb", "3.0.8")
    implementation("org.springframework.boot", "spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot", "spring-boot-starter-web")
    implementation("org.springframework.boot", "spring-boot-starter-tomcat")
    implementation("org.apache.taglibs", "taglibs-standard-impl", "1.2.5")
    implementation("org.apache.tomcat.embed", "tomcat-embed-jasper")
}

tasks {
    test {
        useJUnitPlatform()
    }
}
