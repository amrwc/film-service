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

   ```bash
   git clone git@github.com:amrwc/film-service.git
   cd film-service
   ```

1. Rename `omdb-api.example.properties` in `./src/main/resources`.

   ```bash
   mv ./src/main/resources/omdb-api.example.properties \
      ./src/main/resources/omdb-api.properties
   ```

1. Replace the example API key inside of `omdb-api.example.properties`.

1. Launch MongoDB server.

   ```bash
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

   ```bash
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

[omdb]: https://www.omdbapi.com
[omdb-api-key]: https://www.omdbapi.com/apikey.aspx
[mongodb-installation-guide]: https://docs.mongodb.com/manual/tutorial/install-mongodb-on-os-x
