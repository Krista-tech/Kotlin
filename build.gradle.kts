plugins {
    kotlin("jvm") version "1.8.21"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlin:kotlin-test:1.1.51")
//    testCompileOnly("io.kotlintest:kotlintest-runner-junit5:3.1.9")
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.4.2")


}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}