package com.avides.sqlbuilder.columns.number.doubletype;

import com.avides.sqlbuilder.columns.ColumnDefinition;
import com.avides.sqlbuilder.schema.Table;

public class DoubleColumn extends DoubleTypeColumn<DoubleColumn>
{
    public DoubleColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition);
    }

    public DoubleColumn as(String alias)
    {
        return new DoubleColumn(table, name, alias, columnDefinition);
    }
}
