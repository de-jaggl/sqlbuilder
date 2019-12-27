package com.avides.sqlbuilder.columns.number.integer;

import com.avides.sqlbuilder.columns.ColumnDefinition;
import com.avides.sqlbuilder.schema.Table;

public class MediumIntColumn extends IntegerColumn<MediumIntColumn>
{
    public MediumIntColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition);
    }

    public MediumIntColumn as(String alias)
    {
        return new MediumIntColumn(table, name, alias, columnDefinition);
    }
}
