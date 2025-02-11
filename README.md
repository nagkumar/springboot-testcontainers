## Spring Boot with TestContainers

It's possible to run the Application in development mode on local host from any IDE
by running the class in `test/gae.piaz.springtc.TestApplication` or through the gradle command `bootTestRun`. This
application uses 4 Containers: Redis, Kafka, Postgres, and a custom Python Flask based app.
Prerequisite is to have a docker runtime available and JDK 17.

Furtner details and explanations are available here: https://gaetanopiazzolla.github.io/java/docker/springboot/2023/05/27/springboot-tc.html

---
CURL Requests :

Retrieve all customers from local postgres db:

```shell
curl http://localhost:8181/customersPG
```

Retrieve all customers from external service:

```shell
curl http://localhost:8181/customersFlask
```

Retrieve a customer by name (Redis Cache for Postgres as ID fetched is related to DB not the flask )

 ```shell
curl http://localhost:8181/customersRedis/pino
```

Send a new customer Event through Kafka (then persist in postgres):

```shell
curl -d "{\"id\": 9191,\"name\": \"Curlo\"}" -H "Content-Type: application/json" -X POST http://localhost:8181/customersKafka
```
