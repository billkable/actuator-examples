# README

This project lists some examples of common Actuator
customizations for the following features:

- [Implementing custom endpoints](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html#production-ready-endpoints-custom)
- [Implementing or customizing health indicators](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html#production-ready-health)

## Health Indicators

There are a couple of distinct usage scenarios for
Actuator Health Indicators:

1. Observability
1. Liveness checks

### Observability

Health indicators are useful for showing health status and details of
individual components consumed by a Spring Boot application.

They are useful in both near real-time triage and historical trending scenarios.

For monitoring scenarios,
the aggregate status is usually not as important as the individual component health stats.

If you are running your Spring Boot applications on Pivotal Application
Services (PAS/PCF),
you get the benefit of automated integration between App Manager and
your Spring Boot application as a consequence of the `cloud` profile
and the PCF java buildpack:

1.  A full health status endpoint `/cloudfoundryapplication` set up
    for you without explicit configuration.
1.  The endpoint is secured via Oauth2

See [Using Spring Boot Actuators with Apps Manager](https://docs.pivotal.io/platform/application-service/2-8/console/using-actuators.html)
for more information.

### Liveness checks

Health indicators are also useful for calculating an aggregate status
for use of *liveness check*, where we desire to:

1.  Calculate a single 200 or 500 series response code based from
    health of consumed components within a Spring Boot application.
1.  Result of the response code is used by:
    -   A Server Load Balancer Layer 7 health check to drop unhealthy
        instances from the load balancer server pool.
    -   A container orchestrator (such as Kubernetes or PAS Diego) to
        dispose of unhealthy instances.
        Typically the orchestrator will start a new instance to maintain availability of your app.

In this scenario, the individual health stats are not important,
but the aggregate status and associated HTTP status code are.

## Spring Boot 2.2.x Actuator Health Groups

New in Spring Boot 2.2.x are the
[Health Groups](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/html/production-ready-features.html#health-groups)
feature.

This allows you to set up new actuator health endpoints based on
specific observability or liveness check needs,
are externally configurable,
and require very little to no code.

See the [example code](./actuator-health) how to use
the *Health Groups* feature to implement separate endpoints for
monitoring and liveness checks.

## Custom Endpoints

You can also build your own custom actuator endpoints.

A use case might be to add custom management logic to your app to expose through JMX or an HTTP endpoint.

See the following for more information:

-   [Implementing custom endpoints](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html#production-ready-endpoints-custom)

-   [Example code for adding a custom endpoint](./actuator-custom-endpoint)
