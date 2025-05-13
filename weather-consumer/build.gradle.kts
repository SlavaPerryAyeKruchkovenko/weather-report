plugins {
	java
	id("org.springframework.boot") version "3.4.5"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "dev.kruchkovenko"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	//Redis
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	//Serializer
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.12.7")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.13.4.2")
	//Rabbit
	implementation("org.springframework.boot:spring-boot-starter-amqp")

	implementation("org.springframework.boot:spring-boot-starter")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
