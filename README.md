
## Cheat Sheet

#### To set up connectors
    org.apache.kafka.connect.storage.StringConverter
    
    org.apache.kafka.common.serialization.Serdes$StringSerde
    
 datagen docs: https://github.com/confluentinc/kafka-connect-datagen/tree/master/src/main/resources

#### To package and run from terminal

    mvn clean package 
    java -cp target/example-1.0-SNAPSHOT-jar-with-dependencies.jar src/main/java/org/example/Example.java default


# SCHEMA REGISTRY


### Register a schema for keys

    curl -X POST -H "Content-Type: application/vnd.schema-registry.v1+json" 
    --data '{"schema": "{\"type\": \"string\"}"}' 
    http://schema-registry1:8081/subjects/<topic-name>-key/versions 

    HTTP/1.1 200 OK
    Content-Type: application/vnd.schema-registry.v1+json
    {"id":1}

### Register another schema for values

    curl -X POST -H "Content-Type: application/vnd.schema-registry.v1+json"
    --data '{"schema": "{\"type\": \"string\"}"}'
    http://schema-registry1:8081/subjects/<topic-name>-value/versions
    
    HTTP/1.1 200 OK
    Content-Type: application/vnd.schema-registry.v1+json
    {"id":2}

### Retrieve the schema with id 1

    curl -X GET http://schema-registry:8081/schemas/ids/1

    HTTP/1.1 200 OK
    Content-Type: application/vnd.schema-registry.v1+json
    {"schema": "{\"type\": \"string\"}"}

### Check all the different schema subjects stored in Schema Registry

    curl -X GET http://schema-registry:8081/subjects

    HTTP/1.1 200 OK
    Content-Type: application/vnd.schema-registry.v1+json
    ["my_topic-key", "my_topic-value"]

### View the contents of version 1 of the schema:

    curl schema-registry:8081/subjects/driver-positions-avro-value/versions 

    [1]

    curl -s schema-registry:8081/subjects/driver-positions-avro-value/versions/1 

    {"subject":"driver-positions-avro-value","version":1,"id":1,"schema":"{\"type\":\"record\",\"name\":\"PositionValue\ ",\"namespace\":\"clients.avro\",\"fields\":[{\"name\":\"latitude\",\"type\":\"dou ble\"},{\"name\":\"longitude\",\"type\":\"double\"}]}"}

    curl -s schema-registry:8081/subjects/driver-positions-avro-value/versions/1/schema | jq .

    {
        mstarin@confluent.io "type": "record",
        "name": "PositionValue",
        "namespace": "clients.avro",
        "fields": [
        {
        "name": "latitude",
        "type": "double"
        }, {
        "name": "longitude",
        "type": "double"
        }
        ] 
    }

### Post schema

    curl -X POST -H "Content-Type: application/vnd.schema-registry.v1+json" 
    schema-registry:8081/subjects/driver-positions-avro-value/versions/ 
    -d '{ "schema":"{\"type\":\"record\",\"name\":\"PositionValue\",\"namespace\":\"clients. avro\",\"fields\":[{\"name\":\"latitude\",\"type\":\"double\"},{\"name\":\"longitu de\",\"type\":\"double\"}]}"}'
    
    {"id":1}

### Check the default compatibility setting

    curl schema-registry:8081/config 
    
    {"compatibilityLevel":"BACKWARD"}

### Inspecting schema-registry logs

    docker-compose logs schema-registry | grep '/schemas/ids/1'

### Additional info

https://docs.confluent.io/platform/current/schema-registry/develop/api.html#sr-api-reference

