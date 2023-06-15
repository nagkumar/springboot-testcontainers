plugins {
    id("java")
    id("org.springframework.boot") version "3.1.1-SNAPSHOT"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "gae.piaz"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.spring.io/milestone")
    }
    maven {
        url = uri("https://repo.spring.io/snapshot")
    }
}

dependencies {
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:3.0.4")
    annotationProcessor("org.projectlombok:lombok:1.18.26")

    compileOnly("org.projectlombok:lombok:1.18.26")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.4")
    implementation("org.springframework.boot:spring-boot-starter-web:3.1.0")
    implementation("org.springframework.boot:spring-boot-starter-cache:3.1.0")
    implementation("org.springframework.boot:spring-boot-starter-data-redis:3.0.4")
    implementation("org.springframework.kafka:spring-kafka:3.0.4")

    runtimeOnly("org.postgresql:postgresql:42.5.4")

    testAnnotationProcessor("org.springframework.boot:spring-boot-configuration-processor:3.0.4")

    testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.0")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")

    testImplementation("org.testcontainers:kafka:1.17.6")
    testImplementation("org.testcontainers:postgresql:1.17.6")
    testImplementation("org.projectlombok:lombok:1.18.26")
}
