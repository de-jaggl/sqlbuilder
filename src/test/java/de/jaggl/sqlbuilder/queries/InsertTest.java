package de.jaggl.sqlbuilder.queries;

import static de.jaggl.sqlbuilder.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.dialect.Dialects.SYBASE;
import static de.jaggl.sqlbuilder.functions.Function.now;
import static de.jaggl.sqlbuilder.queries.Queries.insertInto;
import static de.jaggl.sqlbuilder.utils.Indentation.enabled;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.columns.datetime.DateColumn;
import de.jaggl.sqlbuilder.columns.datetime.DateTimeColumn;
import de.jaggl.sqlbuilder.columns.number.doubletype.DoubleColumn;
import de.jaggl.sqlbuilder.columns.number.integer.BigIntColumn;
import de.jaggl.sqlbuilder.columns.number.integer.IntColumn;
import de.jaggl.sqlbuilder.columns.string.VarCharColumn;
import de.jaggl.sqlbuilder.domain.Placeholder;
import de.jaggl.sqlbuilder.schema.Table;

class InsertTest
{
    public static final Table PERSONS = Table.create("persons");

    public static final VarCharColumn FORENAME = PERSONS.varCharColumn("forename").build();
    public static final VarCharColumn LASTNAME = PERSONS.varCharColumn("lastname").build();
    public static final VarCharColumn NICKNAME = PERSONS.varCharColumn("nickname").build();
    public static final IntColumn AGE = PERSONS.intColumn("age").build();
    public static final DoubleColumn SIZE = PERSONS.doubleColumn("size").build();
    public static final IntColumn COUNT = PERSONS.intColumn("count").build();
    public static final DateColumn BIRTHDAY = PERSONS.dateColumn("birthday").build();
    public static final DateColumn DEATHDAY = PERSONS.dateColumn("deathday").build();
    public static final DateTimeColumn LAST_UPDATE = PERSONS.dateTimeColumn("lastUpdate").build();
    public static final BigIntColumn NUMBERS = PERSONS.bigIntColumn("numbers").build();

    @Test
    void testBuildInsert()
    {
        var insert = insertInto(PERSONS)
                .set(NICKNAME, FORENAME)
                .set(FORENAME, "Martin")
                .set(BIRTHDAY, now())
                .set(DEATHDAY, LocalDate.of(2020, 4, 24))
                .set(LAST_UPDATE, LocalDateTime.of(2020, 4, 24, 13, 53))
                .set(COUNT, Integer.valueOf(5))
                .set(AGE, 38)
                .set(SIZE, 175.89)
                .set(NUMBERS, Placeholder.placeholder(NUMBERS))
                .set(LASTNAME, "Schumacher");

        insert.println(MYSQL, enabled());
        insert.println(SYBASE, enabled());

        assertThat(insert.build())
                .isEqualTo("INSERT INTO `persons` SET `persons`.`nickname` = `persons`.`forename`, `persons`.`forename` = 'Martin', `persons`.`birthday` = NOW(), `persons`.`deathday` = '2020-04-24', `persons`.`lastUpdate` = '2020-04-24 13:53:00.000000', `persons`.`count` = 5, `persons`.`age` = 38, `persons`.`size` = 175.89, `persons`.`numbers` = :numbers, `persons`.`lastname` = 'Schumacher'");

        assertThat(insert.build(enabled()))
                .isEqualTo("INSERT INTO\n" //
                        + "  `persons`\n" //
                        + "SET\n" //
                        + "  `persons`.`nickname` = `persons`.`forename`,\n" //
                        + "  `persons`.`forename` = 'Martin',\n" //
                        + "  `persons`.`birthday` = NOW(),\n" //
                        + "  `persons`.`deathday` = '2020-04-24',\n" //
                        + "  `persons`.`lastUpdate` = '2020-04-24 13:53:00.000000',\n" //
                        + "  `persons`.`count` = 5,\n" //
                        + "  `persons`.`age` = 38,\n" //
                        + "  `persons`.`size` = 175.89,\n" //
                        + "  `persons`.`numbers` = :numbers,\n" //
                        + "  `persons`.`lastname` = 'Schumacher'");

        assertThat(insert.build(SYBASE))
                .isEqualTo("INSERT INTO `persons` (`nickname`, `forename`, `birthday`, `deathday`, `lastUpdate`, `count`, `age`, `size`, `numbers`, `lastname`) VALUES (`forename`, 'Martin', NOW(), '2020-04-24', '2020-04-24 13:53:00.000000', 5, 38, 175.89, :numbers, 'Schumacher')");
        assertThat(insert.build(SYBASE, enabled())).isEqualTo("INSERT INTO `persons`\n" //
                + "  (`nickname`, `forename`, `birthday`, `deathday`, `lastUpdate`, `count`, `age`, `size`, `numbers`, `lastname`)\n" //
                + "VALUES\n" //
                + "  (`forename`, 'Martin', NOW(), '2020-04-24', '2020-04-24 13:53:00.000000', 5, 38, 175.89, :numbers, 'Schumacher')");

        assertThat(Insert.copy(insert).build(MYSQL)).isEqualTo(insert.build(MYSQL));
    }
}
