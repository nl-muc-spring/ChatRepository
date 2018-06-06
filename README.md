		# Chat Application

## 1	Exercise – Setup Environment - 2018-05-03
Bring your own device and set up the environment to be able to develop Java Spring Applications.

### Prerequisites

* [Spring Tool STS](https://spring.io/tools/sts/all) - The IDE that we use (Eclipse based)
* [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Install JDK 1.8 or later
* [Buildship Gradle] - inside STS go to Help Marketplace and install Buildship Gradle
* [ChatRepository](https://github.com/nl-muc-spring/ChatRepository) - Clone our git repo from github

### Let it run

Let it run and open the following url

```
localhost:8081
```
And you should see hello world when you open the following url

```
localhost:8081/helloworld
```


## 3	Exercise – Persisting Data - 2018-06-06

## Database (with Docker)

To start up the database, open a shell and navigate to this folder. Then run
```
docker-compse up
```

The Maria database runs on port 3306. In addition to the database, a Aadminer Webservice is also started in the docker stack. You can access the database using the web interface at
```
localhost:8081
```
Before running the Spring service, login to the webinterface using the username *root* and the password `example` (leave the database empty and the server adress is `db`). Then click on `Create database` and call the new database `test`.



