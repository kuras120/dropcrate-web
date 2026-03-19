package com.dropcrate.web;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.containers.wait.strategy.WaitAllStrategy;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

    @Bean
    GenericContainer<?> seaweedfsContainer() {
        return new GenericContainer<>(DockerImageName.parse("chrislusf/seaweedfs:4.17"))
                .withCommand(
                        "server",
                        "-dir=/data",
                        "-ip.bind=0.0.0.0",
                        "-master.port=9333",
                        "-volume.port=8080",
                        "-filer=true",
                        "-filer.port=8888",
                        "-s3=true",
                        "-s3.port=8333"
                )
                .withExposedPorts(9333, 8080, 8888, 8333)
                .waitingFor(
                        new WaitAllStrategy()
                                .withStrategy(Wait.forLogMessage(".*Start Seaweed Master.*", 1))
                                .withStrategy(Wait.forLogMessage(".*Start Seaweed volume server.*", 1))
                                .withStrategy(Wait.forLogMessage(".*Start Seaweed Filer.*", 1))
                                .withStrategy(Wait.forLogMessage(".*Start Seaweed S3 API Server.*", 1))
                                .withStartupTimeout(Duration.ofSeconds(120))
                );
    }

}
