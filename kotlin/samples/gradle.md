# Gradle Docs, tips and configuration

## UBER JAR (FAT JAR)

```kt
plugins {
    kotlin("jvm") version "1.9.23"
    java
    id("application")
}

group = "org.example"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("org.example.MainKt")
}


tasks.jar { // <--- defines a task to create a jar file.

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes["Main-Class"] = "org.example.MainKt"
    }

    // with this line, we collect all required runTime classes in the target jar file, creating an UberJAR.
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}
```
