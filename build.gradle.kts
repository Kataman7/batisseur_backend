plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation(files("libs/javax.json-api-1.1.4.jar"))
    implementation(files("libs/javax.json-1.1.4.jar"))
    implementation(files("libs/Java-WebSocket-1.6.0.jar"))
    implementation(files("libs/slf4j-api-2.0.9.jar"))
    implementation(files("libs/slf4j-simple-2.0.9.jar"))
}

tasks.test {
    useJUnitPlatform()
}