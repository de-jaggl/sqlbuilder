package com.avides.sqlbuilder.queries;

import static com.avides.sqlbuilder.dialect.Dialects.MYSQL;
import static com.avides.sqlbuilder.dialect.Dialects.SYBASE;
import static com.avides.sqlbuilder.functions.Function.now;
import static com.avides.sqlbuilder.queries.Queries.insertInto;
import static com.avides.sqlbuilder.utils.Indentation.enabled;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.avides.sqlbuilder.columns.datetime.DateColumn;
import com.avides.sqlbuilder.columns.string.VarCharColumn;
import com.avides.sqlbuilder.schema.Table;

class InsertTest
{
    public static final Table PERSONS = Table.create("persons");

    public static final VarCharColumn FORENAME = PERSONS.varCharColumn("forename").build();
    public static final VarCharColumn LASTNAME = PERSONS.varCharColumn("lastname").build();
    public static final VarCharColumn NICKNAME = PERSONS.varCharColumn("nickname").build();
    public static final DateColumn BIRTHDAY = PERSONS.dateColumn("birthday").build();

    @Test
    void testBuildInsert()
    {
        var insert = insertInto(PERSONS)
                .set(NICKNAME, FORENAME)
                .set(FORENAME, "Martin")
                .set(BIRTHDAY, now())
                .set(LASTNAME, "Schumacher");

        System.out.println(insert.build(MYSQL, enabled()));
        System.out.println();
        System.out.println(insert.build(SYBASE, enabled()));

        assertThat(insert.build(MYSQL))
                .isEqualTo("INSERT INTO `persons` SET `persons`.`nickname` = `persons`.`forename`, `persons`.`forename` = 'Martin', `persons`.`birthday` = NOW(), `persons`.`lastname` = 'Schumacher'");

        assertThat(insert.build(MYSQL, enabled()))
                .isEqualTo("INSERT INTO\n" //
                        + "  `persons`\n" //
                        + "SET\n" //
                        + "  `persons`.`nickname` = `persons`.`forename`,\n" //
                        + "  `persons`.`forename` = 'Martin',\n" //
                        + "  `persons`.`birthday` = NOW(),\n" //
                        + "  `persons`.`lastname` = 'Schumacher'");

        assertThat(insert.build(MYSQL)).isEqualTo(insert.build(SYBASE));
        assertThat(insert.build(MYSQL, enabled())).isEqualTo(insert.build(SYBASE, enabled()));

        assertThat(Queries.copy(insert).build(MYSQL)).isEqualTo(insert.build(MYSQL));
    }
}
