# RESTful Data Exporter #

## Preface ##
This project provides a RESTFul layer to export data from various data sources.

Currently, it provides data access to any MySQL database but you can extend it to provide access to other data sources too (files, neo4j, etc...).

## How to use ##

This service exposes two REST endpoints. The first one is used to generate a secured table name and the second one should be used to export the data from a table.  
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/33292201dd6a7b30f359)

### Generate token ###
In order to create a "simple" security mechanism a token is generated to get access to a table.
You should use it when you want to provide access to different tables, the generated token is constant per table.

#### Request Schema #####
* **Resource path:** /data/sql/generate
* **HTTP Method:** POST
* **Headers:** Content-Type : application/json
* **Body**:
```js
{
        "token" : "The secret token",
        "name" : "The table name"
}
```


#### Example ####
```bash
    curl -X POST \
      http://localhost:8080/data/sql/generate \
      -H 'content-type: application/json' \
      -d '{
        "token" : "ba65edc4-3071-4261-9cd2-bbd3bffe3eaf",
        "name" : "marketing_test"
    }'
```

### Export Data ###
This endpoint will provide a way to extract data from the connected MySQL database.

#### Request Schema ####
* **Resource path:** /data/sql/export
* **HTTP Method:** POST
* **Headers:** Content-Type : application/json
* **Body**:
```js
    {
        "token" : "The generated table token",
        "fields": ["array", "of", "the", "columns", "to", "export"],
        "condition": "SQL where clause (without where)",
        "groups": ["array", "of", "grouping", "columns"],
        "ordering": ["array", "of", "ordering", "columns"],
        "limit" : 1000
    }
```

#### Example 1 ####
```bash
    curl -X POST \
      http://localhost:8080/data/sql/export \
      -H 'content-type: application/json' \
      -d '{
        "token" : "XqQ8b/9id/9YHykhppDu5w==",
        "fields": ["publisher", "campaign", "downloads", "activations", "sum(revenue)"],
        "condition": "revenue>0",
        "groups": ["publisher"],
        "ordering": [],
        "limit" : 1000
    }'
```

#### Example 2 ####
```bash
    curl -X POST \
      http://localhost:8080/data/sql/export \
      -H 'content-type: application/json' \
      -d '{
        "token" : "XqQ8b/9id/9YHykhppDu5w==",
        "fields": ["publisher AS pub", "campaign AS cmp", "downloads", "activations", "sum(revenue)"],
        "condition": "revenue>0 AND downloads>200",
        "groups": ["publisher"],
        "ordering": ["date_created DESC"],
        "limit" : 1000
    }'
```
## How to build & deploy ##

### Build & Package ###

This is a maven project so you can build the project using:
```bash
    $> mvn clean package
```

This will create the .jar file in the target directory. Since its a basic Spring Boot application you can run it using:

```bash
    #> java \
            -Djava.security.egd=file:/dev/./urandom \
            -Dsql.jdbc="sql url" \
            -Dsql.username="username" \
            -Dsql.password="password" \
            -jar rest-sql-exporter-1.0.jar
```

### Docker ###

If you have docker installed you can build a docker image from the project using:
```bash
    $> mvn clean package docker:build
```

and run it using:

```bash
    $> docker run -p8080:8080 \
        -e sql.jdbc="sql url" \
        -e sql.username="username" \
        -e sql.password="password" \
        --name rest-extractor \
        matang28/rest-sql-exporter
```
