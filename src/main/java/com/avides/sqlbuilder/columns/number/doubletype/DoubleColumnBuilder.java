package com.avides.sqlbuilder.columns.number.doubletype;

import com.avides.sqlbuilder.columns.ColumnDefinition;
import com.avides.sqlbuilder.schema.Table;

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
