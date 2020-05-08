package de.jaggl.sqlbuilder.core.functions;

import static de.jaggl.sqlbuilder.core.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.core.functions.Function.count;
import static de.jaggl.sqlbuilder.core.queries.Queries.select;
import static java.sql.Types.INTEGER;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.core.schema.Table;
import de.jaggl.sqlbuilder.core.testsupport.OtherColumnTestSupport;

class CountTest implements OtherColumnTestSupport
{
    private static final Table TABLE = Table.create("table");

    @Test
    void testCount()
    {
        assertThat(select(count().as("alias")).from(TABLE).build(MYSQL)).isEqualTo("SELECT COUNT(*) AS `alias` FROM `table`");
        assertThat(select(count(getOtherColumn()).as("alias")).from(TABLE).build(MYSQL)).isEqualTo("SELECT COUNT(`table`.`other`) AS `alias` FROM `table`");
    }

    @Test
    void testGetSqlType()
    {
        assertThat(count().getSqlType()).isEqualTo(INTEGER);
    }
}
