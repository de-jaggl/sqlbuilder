package de.jaggl.sqlbuilder.columns.string;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;

public class TextColumnBuilder extends StringColumnBuilder<TextColumnBuilder, TextColumn>
{
    public TextColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    @Override
    protected TextColumn getColumnInstance()
    {
        return new TextColumn(table, name, null, new ColumnDefinition("TEXT", null, isNullable, isDefaultNull, false, false, defaultValue));
    }
}
