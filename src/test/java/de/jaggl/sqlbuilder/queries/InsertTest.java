package de.jaggl.sqlbuilder.queries;

import static de.jaggl.sqlbuilder.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.dialect.Dialects.SYBASE;
import static de.jaggl.sqlbuilder.functions.Function.now;
import static de.jaggl.sqlbuilder.queries.Queries.insertInto;
import static de.jaggl.sqlbuilder.utils.Indentation.enabled;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.columns.datetime.DateColumn;
import de.jaggl.sqlbuilder.columns.number.doubletype.DoubleColumn;
import de.jaggl.sqlbuilder.columns.number.integer.IntColumn;
import de.jaggl.sqlbuilder.columns.string.VarCharColumn;
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

    @Test
    void testBuildInsert()
    {
        var insert = insertInto(PERSONS)
                .set(NICKNAME, FORENAME)
                .set(FORENAME, "Martin")
                .set(BIRTHDAY, now())
                .set(COUNT, Integer.valueOf(5))
                .set(AGE, 38)
                .set(SIZE, 175.89)
                .set(LASTNAME, "Schumacher");

        System.out.println(insert.build(MYSQL, enabled()));
        System.out.println();
        System.out.println(insert.build(SYBASE, enabled()));

        assertThat(insert.build(MYSQL))
                .isEqualTo("INSERT INTO `persons` SET `persons`.`nickname` = `persons`.`forename`, `persons`.`forename` = 'Martin', `persons`.`birthday` = NOW(), `persons`.`count` = 5, `persons`.`age` = 38, `persons`.`size` = 175.89, `persons`.`lastname` = 'Schumacher'");

        assertThat(insert.build(MYSQL, enabled()))
                .isEqualTo("INSERT INTO\n" //
                        + "  `persons`\n" //
                        + "SET\n" //
                        + "  `persons`.`nickname` = `persons`.`forename`,\n" //
                        + "  `persons`.`forename` = 'Martin',\n" //
                        + "  `persons`.`birthday` = NOW(),\n" //
                        + "  `persons`.`count` = 5,\n" //
                        + "  `persons`.`age` = 38,\n" //
                        + "  `persons`.`size` = 175.89,\n" //
                        + "  `persons`.`lastname` = 'Schumacher'");

        assertThat(insert.build(MYSQL)).isEqualTo(insert.build(SYBASE));
        assertThat(insert.build(MYSQL, enabled())).isEqualTo(insert.build(SYBASE, enabled()));

        assertThat(Insert.copy(insert).build(MYSQL)).isEqualTo(insert.build(MYSQL));
    }
}
