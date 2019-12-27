package de.jaggl.sqlbuilder.columns.string;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;

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
