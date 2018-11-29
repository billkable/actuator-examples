# README

This project lists some examples of common Actuator
customizations.

## Aggregate Health Behavior

It is possible to exclude a health indicator from the
Health status aggregation for the hosting application
by overriding the `HealthAggregator`

See the following for more information:

-   [Spring boot actuator health information](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html#production-ready-health)

-   [Example code for filtering a custom health check status from app's app health status](./actuator-health-aggregator)

## Custom Endpoints

You can also build your own custom actuator endpoints.

A use case might be to add custom management logic to your app to expose through JMX or Actuator HTTP endpoint.

See the following for more information:

-   [Implementing custom endpoints](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html#production-ready-endpoints-custom)

-   [Example code for adding a custom endpoint](./actuator-custom-endpoint)
