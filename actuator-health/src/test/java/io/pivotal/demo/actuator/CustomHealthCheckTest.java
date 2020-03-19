package io.pivotal.demo.actuator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomHealthCheckTest {
    @Autowired
    private TestRestTemplate template;

    @Test
    public void testNoShowDetails() {
        ResponseEntity<String> response =
                this.template.getForEntity("/actuator/health",
                        String.class);

        assertThat(Objects.requireNonNull(response.getBody()))
                .doesNotContain(TestConfig.CUSTOM_HEALTH_INDICATOR_NAME);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Test
    public void testLivenessCheckPingOnly() {
        ResponseEntity<String> response =
                this.template.getForEntity("/actuator/health/liveness-check-ping-only",
                        String.class);

        assertThat(Objects.requireNonNull(response.getBody()))
                .doesNotContain(TestConfig.CUSTOM_HEALTH_INDICATOR_NAME);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testLivenessCheck() {
        ResponseEntity<String> response =
                this.template.getForEntity("/actuator/health/liveness-check",
                        String.class);

        assertThat(Objects.requireNonNull(response.getBody()))
                .doesNotContain(TestConfig.CUSTOM_HEALTH_INDICATOR_NAME);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Test
    public void testTelemetryAll() {
        ResponseEntity<String> response =
                this.template.getForEntity("/actuator/health/instance-monitoring-all",
                        String.class);

        assertThat(Objects.requireNonNull(response.getBody()))
                .contains(TestConfig.CUSTOM_HEALTH_INDICATOR_NAME);
        assertThat(Objects.requireNonNull(response.getBody()))
                .contains("ping");
        assertThat(Objects.requireNonNull(response.getBody()))
                .contains("diskSpace");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Test
    public void testTelemetry() {
        ResponseEntity<String> response =
                this.template.getForEntity("/actuator/health/instance-monitoring",
                        String.class);

        assertThat(Objects.requireNonNull(response.getBody()))
                .contains(TestConfig.CUSTOM_HEALTH_INDICATOR_NAME);
        assertThat(Objects.requireNonNull(response.getBody()))
                .contains("diskSpace");
        assertThat(Objects.requireNonNull(response.getBody()))
                .doesNotContain("ping");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
    }
}
