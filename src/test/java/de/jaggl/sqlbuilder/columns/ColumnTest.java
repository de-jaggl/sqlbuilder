package de.jaggl.sqlbuilder.columns;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.columns.string.VarCharColumnBuilder;
import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.testsupport.ColumnTestSupport;

public abstract class ColumnTest<C extends Column, B extends ColumnBuilder<C>> extends ColumnTestSupport<C, B>
{
    @SuppressWarnings("unchecked")
    @Override
    protected B getColumnBuilder(Table table, String name)
    {
        return (B) new VarCharColumnBuilder(table, name);
    }

    @Test
    void testColumnConditions()
    {
        assertCondition(column -> column.isEqualTo(getOtherColumn())).isEqualTo("= `table`.`other`");
        assertCondition(column -> column.isNotEqualTo(getOtherColumn())).isEqualTo("!= `table`.`other`");
        assertCondition(column -> column.isNull()).isEqualTo("IS NULL");
        assertCondition(column -> column.isNotNull()).isEqualTo("IS NOT NULL");
    }
}