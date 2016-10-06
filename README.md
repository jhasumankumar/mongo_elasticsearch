#    Sbring Boot with mongoDB and ElasticSearch. I have mixed up mongoDB and Elasticsearch. Seperated logic with auto aync from mongoDB and elasticsearcg will be available soon.

### It uses 
    MongoDB 3.2.7
    ElasticSearch 2.0.0
    Spring Boot 1.4.0.RELEASE
    Java 8 (Some code that is old like loops and combination of generics and non-generics) :)
    Hsqldb 2.3.3
    Spring Boot Actuator


This application connects to MongoDB database and Elasticsearch and insert into mongoDB and perform indexing and searching using elastic search. 
We can execute on embedded mongdb and elasticsearch without any live node.


### MongoDB should be up and running on your system on default port 27017 or if port is 0 it will use immemory mongoDB
    Start MongoDB window -  E:\git\test\mongodb\bin>mongod.exe --dbpath E:\git\test\mongodb\data
    ---------------- mongo DB --------------
    - spring.data.mongodb.host=localhost
      - spring.data.mongodb.port=27017
    -OR
      - spring.data.mongodb.port=0
    -Dhttp.proxyHost={host} -Dhttp.proxyPort={port} if you want to run on embeded mongodb on port 0 - spring.data.mongodb.port=0

#### Elastic search service should be up and running on your system on default port 9300 or use path where data will be indexed
    Start ElasticSearch window -  E:\git\test\elasticsearch\bin>elasticsearch.bat
    ---------------- elastic search -------------
       spring.data.elasticsearch.cluster-nodes=localhost:9300 
    - OR
       spring.data.elasticsearch.properties.path.home=target/elastic
    spring.data.elasticsearch.properties.transport.tcp.connect_timeout=120s

# Test
Use rest client to test  - I have used redanced rest client to test different API's. There are some test cases that also can be used.


- POST method - http://localhost:9810/mongodb/content/file - consumes - @Consumes(MediaType.MULTIPART_FORM_DATA) parameter name is
   parameter file (Content-Type: multipart/form-data)
- GET method - http://localhost:9810/mongodb/content/{id}/{Search String} - Returns Content Object (from MongoDB)
- GET method - http://localhost:9810/mongodb/content/{id}/{Search String} - Returns Content Object (from elasticSearch based search)

```java
  SearchQuery searchQuery = new NativeSearchQueryBuilder().withIndices("content")
                 .withQuery(QueryBuilders.matchQuery("stringData", searchString)
                         .prefixLength(3))
                 .build();
         return elasticsearchOperations.queryForList(searchQuery, Content.class);
``` 
- After starting application check index using - http://localhost:9200/content/ 

# MongoDB and Elasticsearch Sync
- Have not used mongo-connector to sync data from MongoDB to ElasticSearch. /oplog setting will be required so that elasticsearch can get data from mongoDB.
- It's a simple application. It inserts data in MongoDB as well as ElsticSearch and fetch data from both of them. We can use GridFS to store big file in mongoDB