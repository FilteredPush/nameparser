# nameparser #

An experimental parser for scientific names using javacc.

## Obtain ##

Available from maven central as: 

     <dependency>
         <groupId>org.filteredpush</groupId>
         <artifactId>nameparser</artifactId>
         <version>0.0.1</version>
     </dependency>

Development snapshots available from: 

     https://oss.sonatype.org/content/repositories/snapshots/org/filteredpush/nameparser/

## Using as a Java Library ##

Include the jar (build instructions below) on your classpath, or add as a maven dependency then: 

    import org.filteredpush.nameparser.generated.NameParse
    import org.filteredpush.nameparser.sciname.ScientificName

From the NameParse class you will want either the NameParse.parseToName(String scientificNameString) method which returns a ScientificName object for ease of handling, or NameParse.parseToMap(String scientificNameString) which returns a map of key value pairs, the keys of which may be unstable.

Example invocation: 

    ScientificName parsedName = NameParse.parseToName(String scientificNameString);
    System.out.println(parsedName.getGenericEpithet());

## Build ##

Generate sources using javacc (using the ph-javacc-maven-plugin, requires maven 3): 

    mvn process-resources

Build a jar file:

    mvn clean process-resorces install

## Run ##

java -jar target/nameparser-0.0.1-SNAPSHOT.jar < testnameset.txt

# Developer deployment: # 

To deploy a snapshot to the snapshotRepository:

    mvn clean deploy

To deploy a new release to maven central, set the version in pom.xml to a non-snapshot version, then deploy with the release profile (which adds package signing and deployment to release staging:

    mvn clean deploy -P release

After this, you will need to login to the sonatype oss repository hosting nexus instance (https://oss.sonatype.org/index.html#welcome), find the staged release in the staging repositories, and perform the release.


