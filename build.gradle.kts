plugins {
    kotlin("jvm") version "1.4.10" apply false
}

subprojects {

    group = "com.piotrwalkusz.smartlaw"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
        jcenter()
    }
}