package de.jaggl.sqlbuilder.functions;

import static de.jaggl.sqlbuilder.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.functions.Function.avg;
import static de.jaggl.sqlbuilder.queries.Queries.select;
import static java.sql.Types.DOUBLE;
import static java.sql.Types.INTEGER;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.testsupport.OtherColumnTestSupport;

class AvgTest implements OtherColumnTestSupport
{
    private static final Table TABLE = Table.create("table");

    @Test
    void testCount()
    {
        assertThat(select(avg(getOtherColumn()).as("alias")).from(TABLE).build(MYSQL)).isEqualTo("SELECT AVG(`table`.`other`) AS `alias` FROM `table`");
    }

    @Test
    void testGetSqlType()
    {
        assertThat(avg(getOtherColumn()).getSqlType()).isEqualTo(DOUBLE);
        assertThat(avg(getOtherColumn2()).getSqlType()).isEqualTo(INTEGER);
    }
}
