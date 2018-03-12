# nameparser #

An experimental parser for scientific names using javacc.

## Obtain ##

Not yet available from maven central.

Development snapshot available from: 

     https://oss.sonatype.org/content/repositories/snapshots/org/filteredpush/nameparser/0.0.1-SNAPSHOT/

## Build ##

Generate sources using javacc (using the ph-javacc-maven-plugin, requires maven 3): 

    mvn process-resources

Build a jar file:

    mvn clean process-resorces install

## Run ##

java -jar target/nameparser-0.0.1-SNAPSHOT.jar < testnameset.txt


# Developer deployment: 

To deploy a snapshot to the snapshotRepository:

    mvn clean deploy

To deploy a new release to maven central, set the version in pom.xml to a non-snapshot version, then deploy with the release profile (which adds package signing and deployment to release staging:

    mvn clean deploy -P release

After this, you will need to login to the sonatype oss repository hosting nexus instance (https://oss.sonatype.org/index.html#welcome), find the staged release in the staging repositories, and perform the release.


