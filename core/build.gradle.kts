plugins {
    `java-library`
    kotlin("jvm")
    id("org.jetbrains.kotlin.kapt")
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    compileOnly(project(":annotation-processor", "default"))
    kapt(project(":annotation-processor", "default"))

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.2")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.11.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.11.2")
    implementation("io.github.classgraph:classgraph:4.8.102")
}
