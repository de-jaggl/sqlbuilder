package com.avides.sqlbuilder.columns.configurable;

public interface AutoIncrementableColumnBuilder<T extends AutoIncrementableColumnBuilder<T>>
{
    T autoIncrement(boolean autoIncrement);

    default T autoIncrement()
    {
        return autoIncrement(true);
    }
}
