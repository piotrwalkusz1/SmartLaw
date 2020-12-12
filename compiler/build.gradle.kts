plugins {
    `java-library`
    kotlin("jvm")
}

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
}
