package com.avides.sqlbuilder.columns.string;

import com.avides.sqlbuilder.columns.Column;
import com.avides.sqlbuilder.columns.ColumnBuilder;
import com.avides.sqlbuilder.columns.configurable.DefaultValueColumnBuilder;
import com.avides.sqlbuilder.columns.configurable.NullableColumnBuilder;
import com.avides.sqlbuilder.schema.Table;

public abstract class StringColumnBuilder<T extends StringColumnBuilder<T, C>, C extends Column> extends ColumnBuilder<C>
        implements NullableColumnBuilder<T>, DefaultValueColumnBuilder<T, CharSequence>
{
    protected boolean isNullable = true;
    protected boolean isDefaultNull = true;
    protected CharSequence defaultValue;

    public StringColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T nullable(boolean nullable)
    {
        isNullable = nullable;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T defaultValue(CharSequence value)
    {
        isDefaultNull = false;
        defaultValue = value;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T defaultNull()
    {
        isNullable = true;
        defaultValue = null;
        isDefaultNull = true;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T noDefault()
    {
        defaultValue = null;
        isDefaultNull = false;
        return (T) this;
    }
}
