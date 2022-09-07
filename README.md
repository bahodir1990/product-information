# Product Information microservice using Java, Spring Boot and H2 DB 

Product Information is a sample microservice application you can use to save and get information about the product. 

## Requirements

* CRUD operations for products, categories and attributes of products.
## Getting Started

1. Checkout the project from GitHub

```
git clone https://github.com/bahodir1990/product-information.git

```

2. Open IDE of your choice and Import as existing maven project in your workspace

```
- Import existing maven project
- Run mvn clean install
- Run As Spring Boot Application(ProductInformationApplication.class)

```

3. Default port for the api is 8080


### Prerequisites

* Java 17
* [Maven](https://maven.apache.org/) - Dependency Management

### Maven Dependencies

```
spring-boot-starter-data-jpa
spring-boot-starter-web
springfox-swagger2
springfox-swagger-ui
modelmapper
mapstruct
h2 - Inmemory database
spring-boot-starter-test
spring-security-test
commons-lang3
```
## Swagger

Please find the Rest API documentation in the below url

```
http://localhost:8080/swagger-ui/

```

## H2 In-Memory Database

Make sure to use jdbc:h2:mem:productdb as your jdbc url. If you intend to you use custom database name, please
define datasource properties in application.properties

```
http://localhost:8080/h2-console/

```

## Testing the Bank APP Rest Api

1. Please use the Swagger url to perform CRUD operations.


## Authors

* **Akhmedov Bakhodir**