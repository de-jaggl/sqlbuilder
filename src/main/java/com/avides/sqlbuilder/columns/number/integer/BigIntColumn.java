package com.avides.sqlbuilder.columns.number.integer;

import com.avides.sqlbuilder.columns.ColumnDefinition;
import com.avides.sqlbuilder.schema.Table;

public class BigIntColumn extends IntegerColumn<BigIntColumn>
{
    public BigIntColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition);
    }

    public BigIntColumn as(String alias)
    {
        return new BigIntColumn(table, name, alias, columnDefinition);
    }
}
