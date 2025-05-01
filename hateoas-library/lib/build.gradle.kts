plugins {
    kotlin("jvm") version "1.9.25"
    `java-library`
}

group = "com.hateoasatscale"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {

}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
    withJavadocJar()
    withSourcesJar()
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes["Implementation-Title"] = project.name
        attributes["Implementation-Version"] = project.version
    }
}