package de.jaggl.sqlbuilder.columns.number.doubletype;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;

public class DoubleColumnBuilder extends DoubleTypeColumnBuilder<DoubleColumnBuilder, DoubleColumn>
{
    public DoubleColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    @Override
    protected DoubleColumn getColumnInstance()
    {
        return new DoubleColumn(table, name, null, new ColumnDefinition("DOUBLE", size, isNullable, isDefaultNull, isUnsigned, false, defaultValue));
    }
}
