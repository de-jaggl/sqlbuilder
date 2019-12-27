package com.avides.sqlbuilder.columns.string;

import com.avides.sqlbuilder.columns.ColumnDefinition;
import com.avides.sqlbuilder.schema.Table;

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
