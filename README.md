# WebextLookup for Log4J 2.x

Custom "Web" Lookup (webext) for Log4j2. Open Source Java project under Apache License v2.0

### Current Stable Version is [2.0.0](https://search.maven.org/#search|ga|1|g%3Aorg.javastack%20a%3Alog4j2-webext-jakarta)

---

## DOC

### Webext Lookup

The WebextLookup, like [WebLookup](https://logging.apache.org/log4j/2.0/manual/lookups.html#WebLookup) allows applications to retrieve variables that are associated with the ServletContext. The following table lists various keys that can be retrieved using the `webext:` prefix:

| Key                 | Description |
| :------------------ | :---------- |
| contextBaseFileName | The context base file name of the web application like [tomcat-context](https://tomcat.apache.org/tomcat-10.1-doc/config/context.html#Naming) |

#### Usage Examples

```xml
<!-- log4j2.xml -->
...
<Properties>
    <!-- default value if not contextBaseFileName (not webapp) -->
    <Property name="contextBaseFileName">tomcat</Property>
</Properties>
...
<Appenders>
    <File name="tomcat" fileName="${sys:catalina.base}/logs/${webext:contextBaseFileName}.log"/>
</Appenders>
...
```

```properties
# log4j2.properties 
status = warn
name = PropertiesConfig

# default value if not contextBaseFileName (not webapp)
property.contextBaseFileName = tomcat

appender.tomcat.type = File
appender.tomcat.name = tomcat
appender.tomcat.fileName = ${sys:catalina.base}/logs/${webext:contextBaseFileName}.log
appender.tomcat.layout.type = PatternLayout
appender.tomcat.layout.pattern = %d{ISO8601} [${webext:contextBaseFileName}] [%t] %p %c %MDC %NDC %replace{%m}{[\r\n]}{\\\\n}%n

rootLogger.level = info
rootLogger.appenderRef.tomcat.ref = tomcat
```

---

## MAVEN

Add the dependency to your pom.xml:

###### jakarta.servlet (tomcat 10+; log4j2 2.15.0+)

    <dependency>
        <groupId>org.javastack</groupId>
        <artifactId>log4j2-webext-jakarta</artifactId>
        <version>2.0.0</version>
    </dependency>

###### javax.servlet (tomcat 8.5, 9; log4j2 2.x)

    <dependency>
        <groupId>org.javastack</groupId>
        <artifactId>log4j2-webext</artifactId>
        <version>1.0.1</version>
    </dependency>

---
Inspired in [tomcat-context](https://tomcat.apache.org/tomcat-10.1-doc/config/context.html#Naming) and [log4j2](https://logging.apache.org/log4j/2.0/manual/lookups.html#WebLookup), this code is Java-minimalistic version.
