plugins {
    kotlin("jvm") version "1.9.22"
    id("org.jetbrains.dokka") version "1.9.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    dokkaHtmlPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:1.9.10")
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    // SQLite database plugin
    implementation("org.xerial:sqlite-jdbc:3.36.0.3")
    // PostgreSQL database plugin
    implementation("org.postgresql:postgresql:42.6.0")
}

tasks.dokkaHtml.configure {
    outputDirectory.set(buildDir.resolve("dokka"))
}


tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}