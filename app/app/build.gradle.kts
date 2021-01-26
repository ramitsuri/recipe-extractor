import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val springVersion = "2.4.2"
val jupiterVersion = "5.7.0"
val recipeCoreVersion = "1.1-SNAPSHOT"

plugins {
    val springVersion = "2.4.2"
    id("org.springframework.boot") version springVersion
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    war
    kotlin("jvm") version "1.4.21"
    kotlin("plugin.spring") version "1.4.21"
}

project.base.archivesBaseName = "recipe-app"
group = "com.ramitsuri.recipe.app"
version = "" // Actual version 1.0

repositories {
    jcenter()
    mavenLocal()
}

dependencies {

    testImplementation("org.junit.jupiter:junit-jupiter:$jupiterVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("com.ramitsuri.recipe:core:$recipeCoreVersion")
    implementation("org.springframework.boot:spring-boot-starter-parent:$springVersion")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    //providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
