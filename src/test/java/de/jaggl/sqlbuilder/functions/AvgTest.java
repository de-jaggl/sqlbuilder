package de.jaggl.sqlbuilder.functions;

import static de.jaggl.sqlbuilder.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.functions.Function.avg;
import static de.jaggl.sqlbuilder.queries.Queries.select;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.columns.number.doubletype.DoubleColumn;
import de.jaggl.sqlbuilder.columns.number.doubletype.DoubleColumnBuilder;
import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.testsupport.OtherColumnTestSupport;

class AvgTest implements OtherColumnTestSupport<DoubleColumn, DoubleColumnBuilder>
{
    private static final Table TABLE = Table.create("table");

    @Override
    public DoubleColumnBuilder getColumnBuilder(Table table, String name)
    {
        return new DoubleColumnBuilder(table, name);
    }

    @Test
    public void testCount()
    {
        assertThat(select(avg(getOtherColumn()).as("alias")).from(TABLE).build(MYSQL)).isEqualTo("SELECT AVG(`table`.`other`) AS `alias` FROM `table`");
    }
}