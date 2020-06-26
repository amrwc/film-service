# Film Service

A small Spring Boot application for managing a personal database of films
watched in the past. The goal is to have a seamless way of iteratively adding
new titles along with basic details when they come to mind or right after
watching a new film.

The inspiration came from curiosity about how many films I've ever watched, and
trying to remember the plot of each one. It started as an Excel spreadsheet, but
it soon became tedious to maintain it – spin up MS Excel, add a title I just
remembered or watched, save the document, then perhaps back it up somewhere.
This application does it all for me – it exposes an endpoint that listens for a
list of IMDB URLs, processes the list and fetches each film's details from
[OMDB API][omdb], stores the film details in the database.

## Prerequisites

- JDK 14+,
- Gradle 6.4.1+,
- MongoDB 4.2+,
- [OMDB API key][omdb-api-key].

```bash
# Make sure the terminal session uses correct Java version:
java -version
# If it doesn't and you have JDK 14 installed, run this first:
export JAVA_HOME="$(/usr/libexec/java_home -v 14)"
# Check Gradle version; it must be 6.4.1+ to support Java 14:
gradle -v
```

## Setup

1. Clone the repository.

   ```console
   git clone git@github.com:amrwc/film-service.git
   cd film-service
   ```

1. Rename `omdb-api.example.properties` in `./src/main/resources`.

   ```console
   mv ./src/main/resources/omdb-api.example.properties \
      ./src/main/resources/omdb-api.properties
   ```

1. Replace the example API key inside of `omdb-api.properties`.

1. Launch MongoDB server.

   ```console
   mongod --port 27017 \
          --dbpath ./db/mongodb \
          --config ./db/mongod.conf \
          --fork
   # Make sure it's running
   ps aux | grep -v grep | grep mongod
   ```

   When the `--fork` option is omitted, the process starts in the current
   terminal window.

   Default instructions from can be found in
   [MongoDB installation guide][mongodb-installation-guide].

1. Build and launch the application.

   ```console
   ./gradlew build
   ./gradlew bootRun
   ```

   The Tomcat instance now listens on <http://localhost:8080>.

1. Shut down MongoDB server instance when it's no longer needed.

   If the `--fork` option has been used when launching the server, do the
   following. Otherwise, simply stop the process.

   ```bash
   # Check whether the server is still online
   ps aux | grep -v grep | grep mongod
   # Connect to the `mongo` shell
   mongo --port 27017
   use admin
   db.adminCommand({ shutdown: 1 })
   db.shutdownServer()
   ```

## Debugging

To debug the application, launch Spring Boot with the relevant option and listen
to the `5005` port.

1. Launch Spring Boot with the debugging option.

    ```console
    ./gradlew bootRun --debug-jvm
    ```

1. In the IDE, listen to `localhost:5005` to start the execution.

## Caveats

- The `Film` Mongo document doesn't apply the unique index – when the
  application creates the collection, it only applies the `_id` index. It may
  be related to the Spring auto-configuration of the connection to the database,
  along with the next, `MongoConverter` caveat.
- Before it persists the `Film` document via `FilmRepository`, `FilmService`
  maps the `OmdbFilm` DTO – it can be done using `MappingMongoConverter`, but it
  requires an additional `@Configuration`-annotated class would instantiate the
  database connection, and thus produce the required `@Bean`. Is it worth the
  overhead only to reduce the four lines of a builder? I'll definitely consider
  it again in the future once I learn more about Spring Data and Mongo
  configuration.

[omdb]: https://www.omdbapi.com
[omdb-api-key]: https://www.omdbapi.com/apikey.aspx
[mongodb-installation-guide]: https://docs.mongodb.com/manual/tutorial/install-mongodb-on-os-x
