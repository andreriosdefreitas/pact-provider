
import au.com.dius.pact.provider.gradle.GradleConsumerInfo
import groovy.lang.Closure
import java.io.ByteArrayOutputStream
import java.util.function.Supplier

plugins {
	id("org.springframework.boot") version "3.2.6"
	id("io.spring.dependency-management") version "1.1.5"
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
	id("au.com.dius.pact") version "4.3.10"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("au.com.dius.pact.provider:spring6:4.6.10")
	testImplementation("au.com.dius.pact.provider:gradle:4.6.10")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
	systemProperty("pact.provider.version", getGitHash())
	systemProperty("pact.provider.branch", getGitBranch())
	systemProperty("pact.verifier.publishResults", true)
}

fun getGitHash(): String {
	val stdout = ByteArrayOutputStream()
	exec {
		commandLine = listOf("git", "rev-parse", "--short", "HEAD")
		standardOutput = stdout
	}
	return stdout.toString().trim()
}

fun getGitBranch(): String {
	val stdout = ByteArrayOutputStream()
	exec {
		commandLine = listOf("git", "rev-parse", "--abbrev-ref", "HEAD")
		standardOutput = stdout
	}
	return stdout.toString().trim()
}
