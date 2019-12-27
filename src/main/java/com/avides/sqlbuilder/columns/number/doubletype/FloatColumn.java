package com.avides.sqlbuilder.columns.number.doubletype;

import com.avides.sqlbuilder.columns.ColumnDefinition;
import com.avides.sqlbuilder.schema.Table;

public class FloatColumn extends DoubleTypeColumn<FloatColumn>
{
    public FloatColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition);
    }

    public FloatColumn as(String alias)
    {
        return new FloatColumn(getTable(), getName(), alias, columnDefinition);
    }
}
