# Run locally (with in-memory h2 database)

* mvn clean install
* java -jar ./target/techtest.jar

# Run with docker (with containerized postgres database)

* mvn clean install -Pdocker

# Run rest endpoints

The http directory contains example rest calls