package com.avides.sqlbuilder.columns.number.integer;

import com.avides.sqlbuilder.columns.ColumnDefinition;
import com.avides.sqlbuilder.schema.Table;

public class TinyIntColumnBuilder extends IntegerColumnBuilder<TinyIntColumnBuilder, TinyIntColumn>
{
    public TinyIntColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    @Override
    protected TinyIntColumn getColumnInstance()
    {
        return new TinyIntColumn(table, name, null, new ColumnDefinition("TINYINT", size, isNullable, isDefaultNull, isUnsigned, isAutoIncrement, defaultValue));
    }
}
