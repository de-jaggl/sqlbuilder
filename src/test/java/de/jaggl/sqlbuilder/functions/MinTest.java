package de.jaggl.sqlbuilder.functions;

import static de.jaggl.sqlbuilder.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.functions.Function.min;
import static de.jaggl.sqlbuilder.queries.Queries.select;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.testsupport.OtherColumnTestSupport;

class MinTest implements OtherColumnTestSupport
{
    private static final Table TABLE = Table.create("table");

    @Test
    void testMin()
    {
        assertThat(select(min(getOtherColumn()).as("alias")).from(TABLE).build(MYSQL)).isEqualTo("SELECT MIN(`table`.`other`) AS `alias` FROM `table`");
    }
}
