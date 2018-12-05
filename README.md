# Test APp


## Running Locally

Make sure you have Java and Maven installed. 

```sh
$ git clone https://github.com/dvechirskiy/testapp.git
$ mvn clean install
$ start docker with db under db-test-app folder: docker-compose up -d
$ Running in dev mode: `mvn spring-boot:run`
```

API doc is available at [/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
