package io.pivotal.demo.actuator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActuatorEndpointDemoApplicationTests {
    private final static String COMMAND_EVENT = "Command executed";
    private static final String ACTUATOR_NEW_BACKING_RESOURCE = "/actuator/new-backing-resource";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BackingServiceEndpoint endpoint;

    @Test
    public void testWriteEndpointViaHttp() {
        Map<String,String> postParams = new HashMap<>();

        postParams.put("eventName", COMMAND_EVENT);

        ResponseEntity<Object> responseEntity =
                restTemplate.exchange(
                        RequestEntity.post(URI.create(ACTUATOR_NEW_BACKING_RESOURCE))
                                .body(postParams),
                        Object.class);

        assertThat(responseEntity.getStatusCode().equals(HttpStatus.NO_CONTENT));

        assertThat(endpoint.getEvents().size() == 1);
    }

    @Test
    public void testReadEndpointViaHttp() {
        assertThat(Objects.requireNonNull(
                restTemplate.getForEntity(ACTUATOR_NEW_BACKING_RESOURCE, String[].class)
                        .getBody())[0]
                .equals(COMMAND_EVENT));
    }
}
