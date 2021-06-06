import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.0"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm")
    kotlin("plugin.spring") version "1.4.10"
    id("org.jetbrains.kotlin.kapt")
}

group = "com.piotrwalkusz.smartlaw"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":compiler", "default"))
    implementation("org.springframework.boot:spring-boot-starter-jersey")
    implementation("org.springdoc:springdoc-openapi-ui:1.5.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.litote.kmongo:kmongo:4.2.1")
    implementation(platform("com.github.cloudyrock.mongock:mongock-bom:4.1.19"))
    implementation("com.github.cloudyrock.mongock:mongock-standalone")
    implementation("com.github.cloudyrock.mongock:mongodb-sync-v4-driver")
    testImplementation("org.openjdk.jmh:jmh-core:1.32")
    compileOnly("org.openjdk.jmh:jmh-generator-annprocess:1.32")
    kaptTest("org.openjdk.jmh:jmh-generator-annprocess:1.32")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}
