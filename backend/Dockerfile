# syntax=docker/dockerfile:1

FROM eclipse-temurin:26-jdk-jammy as deps

WORKDIR /build

COPY --chmod=0755 ./backend/mvnw mvnw
COPY ./backend/.mvn/ .mvn/
COPY ./pom.xml ./pom.xml
COPY ./backend/pom.xml ./backend/pom.xml
COPY ./api-specification/ ./api-specification/

RUN --mount=type=bind,source=pom.xml,target=/build/pom.xml \
    --mount=type=cache,target=/root/.m2 ./mvnw dependency:go-offline -DskipTests -f pom.xml

################################################################################

FROM deps as package

WORKDIR /build

COPY ./backend/ ./backend/
COPY ./api-specification/ ./api-specification/

RUN --mount=type=cache,target=/root/.m2 \
    ./mvnw package -DskipTests -f backend/pom.xml && \
    mv backend/target/$(./mvnw help:evaluate -Dexpression=project.artifactId -q -DforceStdout -f backend/pom.xml)-$(./mvnw help:evaluate -Dexpression=project.version -q -DforceStdout -f backend/pom.xml).jar backend/target/app.jar

################################################################################

FROM eclipse-temurin:26-jre-jammy AS final

ARG UID=10001
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser
USER appuser

COPY --from=package /build/backend/target/app.jar app.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]
