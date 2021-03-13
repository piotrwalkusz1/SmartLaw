plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.kapt")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.squareup:kotlinpoet:1.7.2")
    implementation("com.google.auto.service:auto-service:1.0-rc7")
    kapt("com.google.auto.service:auto-service:1.0-rc7")
}