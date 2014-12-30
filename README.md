# Overview

A library that supports using [MongoDB](http://www.mongodb.org/) for data persistence in
[Dropwizard](http://dropwizard.io/) applications.


# Usage

First, add a dependency to your build file.  Releases are published to
[Bintray JCenter](https://bintray.com/bintray/jcenter).  See the [changelog](CHANGES.md) for the latest version.

Gradle:

```groovy
repositories {
    jcenter() // for dropwizard-mongo
}

dependencies {
    compile "com.commercehub.dropwizard:dropwizard-mongo:2.0.0"
}
```

Maven:

settings.xml:
```xml
<profiles>
    <profile>
        <repositories>
            <repository>
                <snapshots>
                    <enabled>false</enabled>
                </snapshots>
                <id>central</id>
                <name>bintray</name>
                <url>http://jcenter.bintray.com</url>
            </repository>
        </repositories>
        <pluginRepositories>
            <pluginRepository>
                <snapshots>
                    <enabled>false</enabled>
                </snapshots>
                <id>central</id>
                <name>bintray-plugins</name>
                <url>http://jcenter.bintray.com</url>
            </pluginRepository>
        </pluginRepositories>
        <id>bintray</id>
    </profile>
</profiles>
<activeProfiles>
    <activeProfile>bintray</activeProfile>
</activeProfiles>
```

pom.xml:
```xml
<dependency>
  <groupId>com.commercehub.dropwizard</groupId>
  <artifactId>dropwizard-mongo</artifactId>
  <version>2.0.0</version>
</dependency>
```

Next, add a field of type `MongoClientFactory` to your application's configuration class:

```java
public class AppConfiguration extends Configuration {

    @Valid
    @NotNull
    private MongoClientFactory mongo;
    
    @JsonProperty
    public MongoClientFactory getMongo() {
        return mongo;
    }
    
    @JsonProperty
    public void setMongo(MongoClientFactory mongo) {
        this.mongo = mongo;
    }

}
```

In your `Application` class, use the `MongoClientFactory` to build a `ManagedMongoClient` and `DB`:

```java
public class App extends Application<AppConfiguration> {

    @Override
    public void run(AppConfiguration config, Environment environment) {
        ManagedMongoClient mongoClient = config.getMongo().build();
        environment.lifecycle().manage(mongoClient);
        DB db = mongoClient.getDB(config.getMongo().getDbName());
    }
    
}
```

Finally, add the necessary bits to your configuration file:

```yaml
mongo:
  uri: mongodb://localhost:27017/?maxPoolSize=50&maxIdleTimeMS=300000
  dbName: mydb
```


# Development

## Releasing
Releases are uploaded to [Bintray](https://bintray.com/) via the
[gradle-release](https://github.com/townsfolk/gradle-release) plugin and
[gradle-bintray-plugin](https://github.com/bintray/gradle-bintray-plugin). To upload a new release, you need to be a
member of the [commercehub-oss Bintray organization](https://bintray.com/commercehub-oss). You need to specify your
Bintray username and API key when uploading. Your API key can be found on your
[Bintray user profile page](https://bintray.com/profile/edit). You can put your username and API key in
`~/.gradle/gradle.properties` like so:

    bintrayUserName = johndoe
    bintrayApiKey = 0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef

Then, to upload the release:

    ./gradlew release

Alternatively, you can specify your Bintray username and API key on the command line:

    ./gradlew -PbintrayUserName=johndoe -PbintrayApiKey=0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef release

The `release` task will prompt you to enter the version to be released, and will create and push a release tag for the
specified version. It will also upload the release artifacts to Bintray.

After the release artifacts have been uploaded to Bintray, they must be published to become visible to users. See
Bintray's [Publishing](https://bintray.com/docs/uploads/uploads_publishing.html) documentation for instructions.

After publishing the release on Bintray, it's also nice to create a GitHub release. To do so:
*   Visit the project's [releases](https://github.com/commercehub-oss/dropwizard-mongo/releases) page
*   Click the "Draft a new release" button
*   Select the tag that was created by the Gradle `release` task
*   Enter a title; typically, this should match the tag (e.g. "1.2.0")
*   Enter a description of what changed since the previous release (see the [changelog](CHANGES.md))
*   Click the "Publish release" button

# License
This library is available under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

(c) All rights reserved Commerce Technologies, Inc.
