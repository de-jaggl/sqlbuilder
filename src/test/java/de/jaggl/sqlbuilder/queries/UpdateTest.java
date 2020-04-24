package de.jaggl.sqlbuilder.queries;

import static de.jaggl.sqlbuilder.conditions.Condition.plain;
import static de.jaggl.sqlbuilder.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.dialect.Dialects.SYBASE;
import static de.jaggl.sqlbuilder.domain.LikeType.AFTER;
import static de.jaggl.sqlbuilder.functions.Function.min;
import static de.jaggl.sqlbuilder.functions.Function.now;
import static de.jaggl.sqlbuilder.queries.Queries.update;
import static de.jaggl.sqlbuilder.queries.Update.clearWheres;
import static de.jaggl.sqlbuilder.utils.Indentation.enabled;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.columns.datetime.DateColumn;
import de.jaggl.sqlbuilder.columns.datetime.DateTimeColumn;
import de.jaggl.sqlbuilder.columns.number.doubletype.DoubleColumn;
import de.jaggl.sqlbuilder.columns.number.integer.IntColumn;
import de.jaggl.sqlbuilder.columns.string.VarCharColumn;
import de.jaggl.sqlbuilder.schema.Table;

class UpdateTest
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

    @Test
    void testBuildUpdate()
    {
        var update = update(PERSONS)
                .set(NICKNAME, FORENAME)
                .set(LASTNAME, "Schumacher")
                .set(BIRTHDAY, now())
                .set(DEATHDAY, LocalDate.of(2020, 4, 24))
                .set(LAST_UPDATE, LocalDateTime.of(2020, 4, 24, 13, 53))
                .set(COUNT, Integer.valueOf(5))
                .set(AGE, 38)
                .set(SIZE, 175.89)
                .where(LASTNAME.isNotLike("Nils", AFTER)
                        .and(min(AGE).isGreaterThanOrEqualTo(50))
                        .and(LASTNAME.isEqualTo("Schumacher")
                                .or(plain("IsNull(COL, '') != ''"))));

        update.println(MYSQL, enabled());
        update.println(SYBASE, enabled());

        assertThat(update.build(MYSQL))
                .isEqualTo("UPDATE `persons` SET `persons`.`nickname` = `persons`.`forename`, `persons`.`lastname` = 'Schumacher', `persons`.`birthday` = NOW(), `persons`.`deathday` = '2020-04-24', `persons`.`lastUpdate` = '2020-04-24 13:53:00.000000', `persons`.`count` = 5, `persons`.`age` = 38, `persons`.`size` = 175.89 WHERE (`persons`.`lastname` NOT LIKE 'Nils%' AND MIN(`persons`.`age`) >= 50 AND (`persons`.`lastname` = 'Schumacher' OR IsNull(COL, '') != ''))");

        assertThat(update.build(MYSQL, enabled())).isEqualTo("UPDATE\n" //
                + "  `persons`\n" //
                + "SET\n" //
                + "  `persons`.`nickname` = `persons`.`forename`,\n" //
                + "  `persons`.`lastname` = 'Schumacher',\n" //
                + "  `persons`.`birthday` = NOW(),\n" //
                + "  `persons`.`deathday` = '2020-04-24',\n" //
                + "  `persons`.`lastUpdate` = '2020-04-24 13:53:00.000000',\n" //
                + "  `persons`.`count` = 5,\n" //
                + "  `persons`.`age` = 38,\n" //
                + "  `persons`.`size` = 175.89\n" //
                + "WHERE\n" //
                + "(\n" //
                + "  `persons`.`lastname` NOT LIKE 'Nils%'\n" //
                + "  AND MIN(`persons`.`age`) >= 50\n" //
                + "  AND\n" //
                + "  (\n" //
                + "    `persons`.`lastname` = 'Schumacher'\n" //
                + "    OR IsNull(COL, '') != ''\n" //
                + "  )\n" //
                + ")");

        assertThat(update.build(MYSQL)).isEqualTo(update.build(SYBASE));
        assertThat(update.build(MYSQL, enabled())).isEqualTo(update.build(SYBASE, enabled()));

        assertThat(Update.copy(update).build(MYSQL)).isEqualTo(update.build(MYSQL));
    }

    @Test
    void testBuildUpdateWithWhereNot()
    {
        var update = update(PERSONS).set(NICKNAME, FORENAME).whereNot(LASTNAME.eq("Schumacher"));

        assertThat(update.build()).isEqualTo("UPDATE `persons` SET `persons`.`nickname` = `persons`.`forename` WHERE NOT `persons`.`lastname` = 'Schumacher'");
    }

    @Test
    void testClearWheres()
    {
        var update = update(PERSONS).set(NICKNAME, FORENAME).where(LASTNAME.eq("Schumacher"));

        assertThat(update.getWhere()).isNotNull();
        clearWheres(update);
        assertThat(update.getWhere()).isNull();
    }
}
