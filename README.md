# sqlbuilder

[![Build Status](https://travis-ci.com/de-jaggl/sqlbuilder.svg?branch=master)](https://travis-ci.com/de-jaggl/sqlbuilder)

A Java-Library to build SQL-Statements

### Simple Example
```java
private static final Table PERSONS = Table.create("persons");

private static final VarCharColumn LASTNAME = PERSONS.varCharColumn("lastname").build();
private static final VarCharColumn FIRSTNAME = PERSONS.varCharColumn("firstname").build();

public static final void mail(String[] args)
{
  Queries.select().from(PERSONS).where(LASTNAME.isEqualTo("Doe")).build(Dialects.MYSQL);
}	
```
This will build
```sql
SELECT * FROM `persons` WHERE `persons`.`lastname` = 'Doe'
```

###	Dependency
```xml
<dependency>
  <groupId>de.jaggl.sqlbuilder</groupId>
  <artifactId>sqlbuilder-core</artifactId>
  <version>2.0.0-SNAPSHOT</version>
</dependency>
```

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
	- DATE
	- DATETIME
	- DECIMAL
	- DOUBLE
	- FLOAT
	- BIGINT
	- INT
	- MEDIUMINT
	- SMALLINT
	- TINYINT
	- CHAR
	- TEXT
	- VARCHAR
	
- Supports SQL-Functions, currently supported are:
	- SUM
	- MIN
	- MAX
	- AVG
	- COUNT
	- NOW
	
- Build queries with or without indentation