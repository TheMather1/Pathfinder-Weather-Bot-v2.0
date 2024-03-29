group = "pathfinder"
version = "2.0"

plugins {
    war
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.spring") version "1.8.21"
    id("org.springframework.boot") version "3.0.6"
    id("io.spring.dependency-management") version "1.1.0"
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("javax.servlet", "jstl", "1.2")
    implementation("net.dv8tion", "JDA", "5.0.0-alpha.21")
    implementation("org.mapdb", "mapdb", "3.0.8")
    implementation("org.springframework.boot", "spring-boot-starter-actuator")
    implementation("org.springframework.boot", "spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot", "spring-boot-starter-web")
    providedRuntime("org.springframework.boot", "spring-boot-starter-tomcat")
    implementation("org.apache.taglibs", "taglibs-standard-impl", "1.2.5")
    implementation("org.apache.tomcat.embed", "tomcat-embed-jasper")
}

war {
    webAppDirName = "src/main"
}

tasks {
    test {
        useJUnitPlatform()
    }
}
