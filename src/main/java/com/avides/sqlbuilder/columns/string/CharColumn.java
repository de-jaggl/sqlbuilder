package com.avides.sqlbuilder.columns.string;

import com.avides.sqlbuilder.columns.ColumnDefinition;
import com.avides.sqlbuilder.schema.Table;

public class CharColumn extends StringColumn<CharColumn>
{
    CharColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition);
    }

    public CharColumn as(String alias)
    {
        return new CharColumn(getTable(), getName(), alias, columnDefinition);
    }
}
