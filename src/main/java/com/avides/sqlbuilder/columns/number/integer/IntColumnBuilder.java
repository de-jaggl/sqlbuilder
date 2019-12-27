package com.avides.sqlbuilder.columns.number.integer;

import com.avides.sqlbuilder.columns.ColumnDefinition;
import com.avides.sqlbuilder.schema.Table;

public class IntColumnBuilder extends IntegerColumnBuilder<IntColumnBuilder, IntColumn>
{
    public IntColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    @Override
    protected IntColumn getColumnInstance()
    {
        return new IntColumn(table, name, null, new ColumnDefinition("INT", size, isNullable, isDefaultNull, isUnsigned, isAutoIncrement, defaultValue));
    }
}
