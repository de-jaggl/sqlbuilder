package de.jaggl.sqlbuilder.queries;

import static de.jaggl.sqlbuilder.conditions.Condition.plain;
import static de.jaggl.sqlbuilder.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.dialect.Dialects.SYBASE;
import static de.jaggl.sqlbuilder.domain.LikeType.AFTER;
import static de.jaggl.sqlbuilder.functions.Function.min;
import static de.jaggl.sqlbuilder.functions.Function.now;
import static de.jaggl.sqlbuilder.queries.Queries.update;
import static de.jaggl.sqlbuilder.utils.Indentation.enabled;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.columns.datetime.DateColumn;
import de.jaggl.sqlbuilder.columns.number.integer.IntColumn;
import de.jaggl.sqlbuilder.columns.string.VarCharColumn;
import de.jaggl.sqlbuilder.queries.Queries;
import de.jaggl.sqlbuilder.schema.Table;

class UpdateTest
{
    public static final Table PERSONS = Table.create("persons");

    public static final VarCharColumn FORENAME = PERSONS.varCharColumn("forename").build();
    public static final VarCharColumn LASTNAME = PERSONS.varCharColumn("lastname").build();
    public static final VarCharColumn NICKNAME = PERSONS.varCharColumn("nickname").build();
    public static final IntColumn AGE = PERSONS.intColumn("age").build();
    public static final DateColumn BIRTHDAY = PERSONS.dateColumn("birthday").build();

    @Test
    void testBuildUpdate()
    {
        var update = update(PERSONS)
                .set(NICKNAME, FORENAME)
                .set(LASTNAME, "Schumacher")
                .set(BIRTHDAY, now())
                .where(LASTNAME.isNotLike("Nils", AFTER)
                        .and(min(AGE).isGreaterThanOrEqualTo(50))
                        .and(LASTNAME.isEqualTo("Schumacher")
                                .or(plain("IsNull(COL, '') != ''"))));

        System.out.println(update.build(MYSQL, enabled()));
        System.out.println();
        System.out.println(update.build(SYBASE, enabled()));

        assertThat(update.build(MYSQL))
                .isEqualTo("UPDATE `persons` SET `persons`.`nickname` = `persons`.`forename`, `persons`.`lastname` = 'Schumacher', `persons`.`birthday` = NOW() WHERE (`persons`.`lastname` NOT LIKE 'Nils%' AND MIN(`persons`.`age`) >= 50 AND (`persons`.`lastname` = 'Schumacher' OR IsNull(COL, '') != ''))");

        assertThat(update.build(MYSQL, enabled())).isEqualTo("UPDATE\n" //
                + "  `persons`\n" //
                + "SET\n" //
                + "  `persons`.`nickname` = `persons`.`forename`,\n" //
                + "  `persons`.`lastname` = 'Schumacher',\n" //
                + "  `persons`.`birthday` = NOW()\n" //
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

        assertThat(Queries.copy(update).build(MYSQL)).isEqualTo(update.build(MYSQL));
    }
}