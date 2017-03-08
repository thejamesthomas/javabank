# javabank [![Build Status](https://snap-ci.com/thejamesthomas/javabank/branch/master/build_image)](https://snap-ci.com/thejamesthomas/javabank/branch/master)
Native Java bindings for Mountebank

## Project Status
Functional but not under active development. I do accept pull requets, though I may not respond to requests for a while.

## To install

You'll need to at least include javabank-core. This includes all of the classes which let you build imposters, predicates, and the like. It also handles serialization to and from JSON.

#### Maven
```
<dependency>
    <groupId>org.mbtest.javabank</groupId>
    <artifactId>javabank-core</artifactId>
    <version>0.4.9</version>
</dependency>
```
#### Gradle
```
compile 'org.mbtest.javabank:javabank-core:0.4.9'
```

If you also need a REST client to talk to Mountebank, you can include the javabank-client library. I use [unirest](http://unirest.io/java.html) as my client of choice. If you already have a rest client in your application, you can save some overhead by continuing to use that instead, though that route will require you to learn a little more about the Mountebank REST API.

#### Maven
```
<dependency>
    <groupId>org.mbtest.javabank</groupId>
    <artifactId>javabank-client</artifactId>
    <version>0.4.7</version>
</dependency>
```
#### Gradle
```
compile 'org.mbtest.javabank:javabank-client:0.4.9'
```
