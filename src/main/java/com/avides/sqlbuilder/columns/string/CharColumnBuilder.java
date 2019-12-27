package com.avides.sqlbuilder.columns.string;

import com.avides.sqlbuilder.columns.ColumnDefinition;
import com.avides.sqlbuilder.domain.IntSize;
import com.avides.sqlbuilder.schema.Table;

public class CharColumnBuilder extends StringColumnBuilder<CharColumnBuilder, CharColumn>
{
    private IntSize size;

    public CharColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    public CharColumnBuilder size(int value)
    {
        size = IntSize.valueOf(Integer.valueOf(value));
        return this;
    }

    public CharColumnBuilder size(Integer value)
    {
        size = IntSize.valueOf(value);
        return this;
    }

    @Override
    protected CharColumn getColumnInstance()
    {
        return new CharColumn(table, name, null, new ColumnDefinition("CHAR", size, isNullable, isDefaultNull, false, false, defaultValue));
    }
}
