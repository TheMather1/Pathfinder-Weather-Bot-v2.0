group = "pathfinder"
version = "2.0"

plugins {
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.spring") version "1.6.0"
    id("org.springframework.boot") version "2.5.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

repositories {
    jcenter()
//    maven("https://maven.pkg.github.com/TheMather1/temporal-collections")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("javax.servlet", "jstl", "1.2")
    implementation("net.dv8tion", "JDA", "4.2.0_247")
    implementation("club.minnced", "jda-reactor", "1.2.0")
//    implementation("no.mather.temporal", "temporal-collections", "1.0.1")
    implementation("org.springframework.boot", "spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot", "spring-boot-starter-web")
    implementation("org.springframework.boot", "spring-boot-starter-tomcat")
    implementation("org.apache.taglibs", "taglibs-standard-impl", "1.2.5")
    implementation("org.apache.tomcat.embed", "tomcat-embed-jasper")
    testImplementation("org.junit.jupiter", "junit-jupiter", "5.5.2")
}

tasks {
    test {
        useJUnitPlatform()
    }
}