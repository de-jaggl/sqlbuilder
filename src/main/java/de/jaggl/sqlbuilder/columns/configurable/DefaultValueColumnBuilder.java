package de.jaggl.sqlbuilder.columns.configurable;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public interface DefaultValueColumnBuilder<T extends DefaultValueColumnBuilder<T, V>, V>
{
    T defaultValue(V value);

    T defaultNull();

    T noDefault();
}
