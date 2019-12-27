package com.avides.sqlbuilder.columns.number;

import com.avides.sqlbuilder.columns.ColumnBuilder;
import com.avides.sqlbuilder.columns.configurable.DefaultValueColumnBuilder;
import com.avides.sqlbuilder.columns.configurable.NullableColumnBuilder;
import com.avides.sqlbuilder.columns.configurable.UnsignableColumnBuilder;
import com.avides.sqlbuilder.schema.Table;

public abstract class NumberColumnBuilder<T extends NumberColumnBuilder<T, N, C>, N extends Number, C extends NumberColumn<C, N>> extends ColumnBuilder<C>
        implements NullableColumnBuilder<T>, DefaultValueColumnBuilder<T, N>, UnsignableColumnBuilder<T>
{
    protected boolean isNullable = true;
    protected boolean isDefaultNull = true;
    protected boolean isUnsigned;
    protected N defaultValue;

    public NumberColumnBuilder(Table table, String name)
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
    public T unsigned(boolean unsigned)
    {
        isUnsigned = unsigned;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T defaultValue(N value)
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
