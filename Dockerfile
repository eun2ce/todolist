FROM eclipse-temurin:21 AS builder

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM eclipse-temurin:21

# 빌드 산출물(프로젝트 JAR)을 복사 - 실제로는 CI/CD에서 'mvn package' 등으로 만든 jar파일을 복사한다고 가정
COPY --from=builder build/libs/*.jar application.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/application.jar", "--spring.profiles.active=prod"]