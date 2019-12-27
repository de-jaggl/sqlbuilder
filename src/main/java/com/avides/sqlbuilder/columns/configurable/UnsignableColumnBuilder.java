package com.avides.sqlbuilder.columns.configurable;

public interface UnsignableColumnBuilder<T extends UnsignableColumnBuilder<T>>
{
    T unsigned(boolean unsigned);

    default T unsigned()
    {
        return unsigned(true);
    }
}
