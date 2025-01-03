# spring-boot-demo

# Set Up
### build a docker container
```
$ docker run --name scylla -p 9042:9042 -d scylladb/scylla
```
### connect to the database
```
docker exec -it scylla cqlsh
```



