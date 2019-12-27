package de.jaggl.sqlbuilder.queries;

import static de.jaggl.sqlbuilder.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.dialect.Dialects.SYBASE;
import static de.jaggl.sqlbuilder.domain.LikeType.AFTER;
import static de.jaggl.sqlbuilder.functions.Function.min;
import static de.jaggl.sqlbuilder.queries.Queries.deleteFrom;
import static de.jaggl.sqlbuilder.utils.Indentation.enabled;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.columns.number.integer.IntColumn;
import de.jaggl.sqlbuilder.columns.string.VarCharColumn;
import de.jaggl.sqlbuilder.queries.Queries;
import de.jaggl.sqlbuilder.schema.Table;

class DeleteTest
{
    private static final Table PERSONS = Table.create("persons");

    private static final VarCharColumn LASTNAME = PERSONS.varCharColumn("lastname").build();
    private static final IntColumn AGE = PERSONS.intColumn("age").build();

    @Test
    void testBuildDelete()
    {
        var delete = deleteFrom(PERSONS)
                .where(LASTNAME.isNotLike("Nils", AFTER)
                        .and(min(AGE).isGreaterThanOrEqualTo(50))
                        .and(LASTNAME.isEqualTo("Schumacher")
                                .or(LASTNAME.isEqualTo("Kuenzel"))))
                .limit(10);

        System.out.println(delete.build(MYSQL, enabled()));
        System.out.println();
        System.out.println(delete.build(SYBASE, enabled()));

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

        assertThat(Queries.copy(delete).build(MYSQL)).isEqualTo(delete.build(MYSQL));
    }
}
