# SQLbuilder

[![Maven Central](https://img.shields.io/maven-metadata/v/http/central.maven.org/maven2/de/jaggl/sqlbuilder/sqlbuilder-core/maven-metadata.xml.svg)](https://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22de.jaggl.sqlbuilder%22%20AND%20a%3A%22sqlbuilder-core%22)
[![Release](https://github.com/de-jaggl/sqlbuilder/workflows/release/badge.svg)](https://github.com/de-jaggl/sqlbuilder/actions)
[![Nightly build](https://github.com/de-jaggl/sqlbuilder/workflows/nightly/badge.svg)](https://github.com/de-jaggl/sqlbuilder/actions)
[![javadoc](https://javadoc.io/badge2/de.jaggl.sqlbuilder/sqlbuilder-core/javadoc.svg?)](https://javadoc.io/doc/de.jaggl.sqlbuilder/sqlbuilder-core)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=de-jaggl_sqlbuilder&metric=alert_status)](https://sonarcloud.io/dashboard?id=de-jaggl_sqlbuilder)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=de-jaggl_sqlbuilder&metric=coverage)](https://sonarcloud.io/dashboard?id=de-jaggl_sqlbuilder)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=de-jaggl_sqlbuilder&metric=sqale_index)](https://sonarcloud.io/dashboard?id=de-jaggl_sqlbuilder)
[![GitHub](https://img.shields.io/github/license/de-jaggl/sqlbuilder)](https://github.com/de-jaggl/sqlbuilder/blob/master/LICENSE)
[![Gitter](https://badges.gitter.im/de-jaggl/community.svg)](https://gitter.im/de-jaggl/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

A Java-Library to build SQL-Statements

### Dependency

```xml
<dependency>
  <groupId>de.jaggl.sqlbuilder</groupId>
  <artifactId>sqlbuilder-core</artifactId>
  <version>2.4.2</version>
</dependency>
```

### Simple Example

```java
private static final Table PERSONS = Table.create("persons");
private static final VarCharColumn LASTNAME = PERSONS.varCharColumn("lastname").build();

public static final void main(String[] args)
{
  Queries.select()
  	.from(PERSONS)
  	.where(LASTNAME.eq("Doe"))
  	.print();
}	
```
This will output:
```sql
SELECT * FROM `persons` WHERE `persons`.`lastname` = 'Doe'
```
To get the SQL-statement as a string, call `build()` instead of `print()`

### Features

- Build SQL-queries in different dialects, currently supported are:
	- MySQL
	- Sybase
	
- Currently supported queries are:
  - SELECT
  - INSERT
  - UPDATE
  - DELETE
  - CREATE TABLE
  
- Type-safe query-building. Currently supported column-datatypes are:
	- CHAR, VARCHAR, TEXT
	- INT, BIGINT, MEDIUMINT, SMALLINT, TINYINT
	- DOUBLE, FLOAT, DECIMAL
	- DATE, DATETIME
	
- Supports SQL-Functions, currently supported are:
	- SUM
	- MIN
	- MAX
	- AVG
	- COUNT
	- NOW
	
- Build queries with or without indentation

### Choose Dialect

By default the MySQL-Dialect is chosen. To change the Dialect, you can pass your wanted Dialect to the `print()` or `build()`-method. The known Dialects are collected in the Utility-Class `Dialects`. Simple Example for choose the known Sybase-Dialect:
```java
Queries.select()
  .from(PERSONS)
  .limit(100, 10)
  .print(Dialects.SYBASE);
```
This will output:
```sql
SELECT TOP 100 START AT 11 * FROM `persons`
```
It is also possible to glabally change the default-Dialect. To do so, set the system-property `sqlbuilder.defaultDialect` to the name of the Dialect you want.

### Indentation

Just add `Indentation.enabled()` to the `print()` or `build()`-method as follows:
```java
Queries.select()
  .from(PERSONS)
  .where(LASTNAME.eq("Doe"))
  .print(Indentation.enabled());
```
This will output:
```sql
SELECT
  *
FROM
  `persons`
WHERE `persons`.`lastname` = 'Doe'
```
