package de.jaggl.sqlbuilder.columns.configurable;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
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
