package com.avides.sqlbuilder.columns.string;

import com.avides.sqlbuilder.columns.ColumnDefinition;
import com.avides.sqlbuilder.schema.Table;

public class TextColumn extends StringColumn<TextColumn>
{
    TextColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition);
    }

    public TextColumn as(String alias)
    {
        return new TextColumn(getTable(), getName(), alias, columnDefinition);
    }
}
