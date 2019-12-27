package com.avides.sqlbuilder.columns.number.integer;

import com.avides.sqlbuilder.columns.ColumnDefinition;
import com.avides.sqlbuilder.schema.Table;

public class SmallIntColumnBuilder extends IntegerColumnBuilder<SmallIntColumnBuilder, SmallIntColumn>
{
    public SmallIntColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    @Override
    protected SmallIntColumn getColumnInstance()
    {
        return new SmallIntColumn(table, name, null, new ColumnDefinition("SMALLINT", size, isNullable, isDefaultNull, isUnsigned, isAutoIncrement, defaultValue));
    }
}
