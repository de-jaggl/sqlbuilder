package com.avides.sqlbuilder.columns.number.integer;

import com.avides.sqlbuilder.columns.ColumnDefinition;
import com.avides.sqlbuilder.schema.Table;

public class BigIntColumnBuilder extends IntegerColumnBuilder<BigIntColumnBuilder, BigIntColumn>
{
    public BigIntColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    @Override
    protected BigIntColumn getColumnInstance()
    {
        return new BigIntColumn(table, name, null, new ColumnDefinition("BIGINT", size, isNullable, isDefaultNull, isUnsigned, isAutoIncrement, defaultValue));
    }
}
