package de.jaggl.sqlbuilder.core.queries;

import static de.jaggl.sqlbuilder.core.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.core.dialect.Dialects.SYBASE;
import static de.jaggl.sqlbuilder.core.domain.LikeType.AFTER;
import static de.jaggl.sqlbuilder.core.functions.Function.min;
import static de.jaggl.sqlbuilder.core.queries.Delete.clearLimit;
import static de.jaggl.sqlbuilder.core.queries.Delete.clearWheres;
import static de.jaggl.sqlbuilder.core.queries.Queries.deleteFrom;
import static de.jaggl.sqlbuilder.core.utils.Indentation.enabled;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.core.columns.number.integer.IntColumn;
import de.jaggl.sqlbuilder.core.columns.string.VarCharColumn;
import de.jaggl.sqlbuilder.core.schema.Table;

class DeleteTest
{
    private static final Table PERSONS = Table.create("persons");

    private static final VarCharColumn LASTNAME = PERSONS.varCharColumn("lastname").build();
    private static final IntColumn AGE = PERSONS.intColumn("age").build();

    @Test
    void testBuildSimpleDelete()
    {
        assertThat(deleteFrom(PERSONS).where(LASTNAME.isEqualTo("Schumacher")).build(SYBASE))
                .isEqualTo("DELETE FROM `persons` WHERE `persons`.`lastname` = 'Schumacher'");
    }

    @Test
    void testBuildDelete()
    {
        var delete = deleteFrom(PERSONS)
                .where(LASTNAME.isNotLike("Nils", AFTER)
                        .and(min(AGE).isGreaterThanOrEqualTo(50))
                        .and(LASTNAME.isEqualTo("Schumacher")
                                .or(LASTNAME.isEqualTo("Kuenzel"))))
                .limit(10);

        delete.println(MYSQL, enabled());
        delete.println(SYBASE, enabled());

        assertThat(delete.build(MYSQL))
                .isEqualTo("DELETE FROM `persons` WHERE (`persons`.`lastname` NOT LIKE 'Nils%' AND MIN(`persons`.`age`) >= 50 AND (`persons`.`lastname` = 'Schumacher' OR `persons`.`lastname` = 'Kuenzel')) LIMIT 10");

        assertThat(delete.build(MYSQL, enabled()))
                .isEqualTo("DELETE FROM\n" //
                        + "  `persons`\n" //
                        + "WHERE\n" //
                        + "(\n" //
                        + "  `persons`.`lastname` NOT LIKE 'Nils%'\n" //
                        + "  AND MIN(`persons`.`age`) >= 50\n" //
                        + "  AND\n" //
                        + "  (\n" //
                        + "    `persons`.`lastname` = 'Schumacher'\n" //
                        + "    OR `persons`.`lastname` = 'Kuenzel'\n" //
                        + "  )\n" //
                        + ")\n" //
                        + "LIMIT 10");

        assertThat(delete.build(SYBASE))
                .isEqualTo("DELETE TOP 10 FROM `persons` WHERE (`persons`.`lastname` NOT LIKE 'Nils%' AND MIN(`persons`.`age`) >= 50 AND (`persons`.`lastname` = 'Schumacher' OR `persons`.`lastname` = 'Kuenzel'))");

        assertThat(delete.build(SYBASE, enabled()))
                .isEqualTo("DELETE TOP 10 FROM\n" //
                        + "  `persons`\n" //
                        + "WHERE\n" //
                        + "(\n" //
                        + "  `persons`.`lastname` NOT LIKE 'Nils%'\n" //
                        + "  AND MIN(`persons`.`age`) >= 50\n" //
                        + "  AND\n" //
                        + "  (\n" //
                        + "    `persons`.`lastname` = 'Schumacher'\n" //
                        + "    OR `persons`.`lastname` = 'Kuenzel'\n" //
                        + "  )\n" //
                        + ")");

        assertThat(Delete.copy(delete).build(MYSQL)).isEqualTo(delete.build(MYSQL));
    }

    @Test
    void testBuildDeleteWithWhereNot()
    {
        var delete = deleteFrom(PERSONS).whereNot(LASTNAME.isLike("Nils", AFTER));

        delete.println(MYSQL, enabled());
        delete.println(SYBASE, enabled());

        assertThat(delete.build(MYSQL)).isEqualTo("DELETE FROM `persons` WHERE NOT `persons`.`lastname` LIKE 'Nils%'");

        assertThat(delete.build(MYSQL, enabled()))
                .isEqualTo("DELETE FROM\n" //
                        + "  `persons`\n" //
                        + "WHERE NOT `persons`.`lastname` LIKE 'Nils%'");

        assertThat(Delete.copy(delete).build(MYSQL)).isEqualTo(delete.build(MYSQL));
    }

    @Test
    void testClearWheres()
    {
        var delete = deleteFrom(PERSONS).where(LASTNAME.eq("Nils"));

        assertThat(delete.getWhere()).isNotNull();
        clearWheres(delete);
        assertThat(delete.getWhere()).isNull();
    }

    @Test
    void testClearLimit()
    {
        var delete = deleteFrom(PERSONS).limit(5);

        assertThat(delete.getLimitation()).isNotNull();
        clearLimit(delete);
        assertThat(delete.getLimitation()).isNull();
    }
}
