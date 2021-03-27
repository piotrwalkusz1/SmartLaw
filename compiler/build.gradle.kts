plugins {
    `java-library`
    kotlin("jvm")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> { kotlinOptions.jvmTarget = "1.8" }

dependencies {
    implementation(project(":core", "default"))
    implementation("org.freemarker:freemarker:2.3.14")
    implementation("org.docx4j:docx4j:6.1.2") {
        exclude(group = "log4j", module = "log4j")
        exclude(group = "org.slf4j", module = "slf4j-log4j12")
    }
    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("org.glassfish.jaxb:jaxb-runtime:2.3.3")
    implementation("pl.allegro.finance:tradukisto:1.8.0")
    implementation("org.codehaus.groovy:groovy-all:3.0.7")
    implementation("com.michael-bull.kotlin-result:kotlin-result:1.1.11")
    implementation("net.pearx.kasechange:kasechange:1.3.0")
}
