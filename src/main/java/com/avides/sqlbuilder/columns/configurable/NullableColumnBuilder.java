package com.avides.sqlbuilder.columns.configurable;

public interface NullableColumnBuilder<T extends NullableColumnBuilder<T>>
{
    T nullable(boolean nullable);

    default T nullable()
    {
        return nullable(true);
    }

    default T notNull()
    {
        return nullable(false);
    }
}
