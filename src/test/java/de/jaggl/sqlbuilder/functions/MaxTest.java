package de.jaggl.sqlbuilder.functions;

import static de.jaggl.sqlbuilder.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.functions.Function.max;
import static de.jaggl.sqlbuilder.queries.Queries.select;
import static java.sql.Types.DOUBLE;
import static java.sql.Types.INTEGER;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.testsupport.OtherColumnTestSupport;

class MaxTest implements OtherColumnTestSupport
{
    private static final Table TABLE = Table.create("table");

    @Test
    void testMax()
    {
        assertThat(select(max(getOtherColumn()).as("alias")).from(TABLE).build(MYSQL)).isEqualTo("SELECT MAX(`table`.`other`) AS `alias` FROM `table`");
    }

    @Test
    void testGetSqlType()
    {
        assertThat(max(getOtherColumn()).getSqlType()).isEqualTo(DOUBLE);
        assertThat(max(getOtherColumn2()).getSqlType()).isEqualTo(INTEGER);
    }
}
