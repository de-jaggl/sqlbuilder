package de.jaggl.sqlbuilder.core.queries;

import static de.jaggl.sqlbuilder.core.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.core.dialect.Dialects.SYBASE;
import static de.jaggl.sqlbuilder.core.queries.Queries.createTable;
import static de.jaggl.sqlbuilder.core.utils.Indentation.enabled;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.core.columns.datetime.DateColumn;
import de.jaggl.sqlbuilder.core.columns.datetime.DateTimeColumn;
import de.jaggl.sqlbuilder.core.columns.number.doubletype.DoubleColumn;
import de.jaggl.sqlbuilder.core.columns.number.integer.IntColumn;
import de.jaggl.sqlbuilder.core.columns.string.VarCharColumn;
import de.jaggl.sqlbuilder.core.schema.Schema;
import de.jaggl.sqlbuilder.core.schema.Table;

class CreateTableTest
{
    public static final Schema DBA = Schema.create("dba");

    public static final Table PERSONS = DBA.table("persons");

    public static final VarCharColumn FORENAME = PERSONS.varCharColumn("forename").size(50).noDefault().build();
    public static final VarCharColumn LASTNAME = PERSONS.varCharColumn("lastname").size(50).defaultNull().build();
    public static final VarCharColumn NICKNAME = PERSONS.varCharColumn("nickname").size(30).defaultValue("Schubi").build();
    public static final IntColumn AGE = PERSONS.intColumn("age").size(5).unsigned().notNull().noDefault().autoIncrement().build();
    public static final DoubleColumn SIZE = PERSONS.doubleColumn("size").size(2.2).unsigned().notNull().defaultValue(55.8).build();
    public static final DateColumn BIRTHDAY = PERSONS.dateColumn("birthday").notNull().build();
    public static final DateTimeColumn HAPPENING = PERSONS.dateTimeColumn("happening").notNull().build();

    @Test
    void testCreateTable()
    {
        var createTable = createTable(PERSONS);
        createTable.println();
        createTable.println(SYBASE, enabled());

        assertThat(createTable.build(MYSQL))
                .isEqualTo("CREATE TABLE `dba`.`persons` (`forename` VARCHAR(50), `lastname` VARCHAR(50) DEFAULT NULL, `nickname` VARCHAR(30) DEFAULT 'Schubi', `age` INT(5) UNSIGNED NOT NULL AUTO_INCREMENT, `size` DOUBLE(2,2) UNSIGNED NOT NULL DEFAULT 55.8, `birthday` DATE NOT NULL, `happening` DATETIME NOT NULL, PRIMARY KEY (`age`))");

        assertThat(createTable.build(MYSQL, enabled()))
                .isEqualTo("CREATE TABLE `dba`.`persons`\n" //
                        + "(\n" //
                        + "  `forename` VARCHAR(50),\n" //
                        + "  `lastname` VARCHAR(50) DEFAULT NULL,\n" //
                        + "  `nickname` VARCHAR(30) DEFAULT 'Schubi',\n" //
                        + "  `age` INT(5) UNSIGNED NOT NULL AUTO_INCREMENT,\n" //
                        + "  `size` DOUBLE(2,2) UNSIGNED NOT NULL DEFAULT 55.8,\n" //
                        + "  `birthday` DATE NOT NULL,\n" //
                        + "  `happening` DATETIME NOT NULL,\n" //
                        + "  PRIMARY KEY (`age`)\n" //
                        + ")");

        assertThat(createTable.build(MYSQL)).isEqualTo(createTable.build(SYBASE));
        assertThat(createTable.build(MYSQL, enabled())).isEqualTo(createTable.build(SYBASE, enabled()));

        assertThat(CreateTable.copy(createTable).build(MYSQL)).isEqualTo(createTable.build(MYSQL));
    }
}
