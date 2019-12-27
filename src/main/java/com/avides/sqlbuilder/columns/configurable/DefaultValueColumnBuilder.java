package com.avides.sqlbuilder.columns.configurable;

public interface DefaultValueColumnBuilder<T extends DefaultValueColumnBuilder<T, V>, V>
{
    T defaultValue(V value);

    T defaultNull();

    T noDefault();
}
