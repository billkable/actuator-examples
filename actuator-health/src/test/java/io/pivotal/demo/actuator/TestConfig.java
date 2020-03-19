package io.pivotal.demo.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    static String CUSTOM_HEALTH_INDICATOR_NAME = "custom1";

    @Bean
    public HealthIndicator custom1HealthIndicator(){
        return () -> Health.down().build();
    }
}