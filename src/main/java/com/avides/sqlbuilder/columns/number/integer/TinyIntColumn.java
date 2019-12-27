package com.avides.sqlbuilder.columns.number.integer;

import com.avides.sqlbuilder.columns.ColumnDefinition;
import com.avides.sqlbuilder.schema.Table;

public class TinyIntColumn extends IntegerColumn<TinyIntColumn>
{
    public TinyIntColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition);
    }

    public TinyIntColumn as(String alias)
    {
        return new TinyIntColumn(table, name, alias, columnDefinition);
    }
}
