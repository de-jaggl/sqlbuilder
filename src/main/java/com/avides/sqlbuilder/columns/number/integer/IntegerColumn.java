package com.avides.sqlbuilder.columns.number.integer;

import com.avides.sqlbuilder.columns.ColumnDefinition;
import com.avides.sqlbuilder.columns.number.NumberColumn;
import com.avides.sqlbuilder.schema.Table;

public abstract class IntegerColumn<T extends IntegerColumn<T>> extends NumberColumn<T, Integer>
{
    public IntegerColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition);
    }
}
