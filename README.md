# Test Banco Pichincha | Inventarios #


## Local Development ##

### Requirements ###

- Install [JavaSDK](https://www.oracle.com/java/technologies/downloads/)
or [OpenJDK](https://openjdk.java.net/).
- Install [Maven](https://maven.apache.org/install.html)

### Local Server ###

```shell
mvn spring-boot:run
```


### Swagger ###

I use Swagger to document the API.

```shell
api/v1/swagger-ui/index.html
```

### Testing ###

I use JaCoCo to run tests and generate reports. In order you to be
able to see the JaCoCo report, you must first run:

```shell
mvn clean
mvn verify
```

After the commands' execution the test report will be available
in `/target/site/jacoco/index.html`. You can open it with your
favorite browser.
