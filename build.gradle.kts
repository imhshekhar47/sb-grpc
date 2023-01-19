import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.google.protobuf.gradle.*

plugins {
    id("org.springframework.boot") version "2.7.5"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"

    id("com.google.protobuf") version "0.8.19"
}

group = "org.hshekhar"
version = "0.0.1-SNAPSHOT"
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

sourceSets.named("main") {
    java.srcDirs(
        "src/main/java",
        "src/main/kotlin",
        "${buildDir}/proto/src/main/proto",
        "${buildDir}/generated/source/proto/main/java",
        "${buildDir}/generated/source/proto/main/kotlin",
        "${buildDir}/generated/source/proto/main/grpc",
        "${buildDir}/generated/source/proto/main/grpckt"
    )
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // grpc
    implementation("io.grpc:grpc-stub:1.46.0")
    //runtimeOnly("io.grpc:grpc-netty:1.46.0")
    implementation("io.grpc:grpc-protobuf:1.46.0")
    implementation("com.google.protobuf:protobuf-java-util:3.20.1")
    implementation("com.google.protobuf:protobuf-kotlin:3.20.1")
    implementation("io.grpc:grpc-kotlin-stub:1.3.0")

    implementation("net.devh:grpc-server-spring-boot-starter:2.13.1.RELEASE")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.3"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.48.0"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.3.0:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
            it.builtins {
                id("kotlin")
            }
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
