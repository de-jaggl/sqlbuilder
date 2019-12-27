package com.avides.sqlbuilder.columns.number.doubletype;

import com.avides.sqlbuilder.columns.ColumnDefinition;
import com.avides.sqlbuilder.schema.Table;

public class FloatColumnBuilder extends DoubleTypeColumnBuilder<FloatColumnBuilder, FloatColumn>
{
    public FloatColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    @Override
    protected FloatColumn getColumnInstance()
    {
        return new FloatColumn(table, name, null, new ColumnDefinition("FLOAT", size, isNullable, isDefaultNull, isUnsigned, false, defaultValue));
    }
}
