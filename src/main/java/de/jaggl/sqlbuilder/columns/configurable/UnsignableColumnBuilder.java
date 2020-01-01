package de.jaggl.sqlbuilder.columns.configurable;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public interface UnsignableColumnBuilder<T extends UnsignableColumnBuilder<T>>
{
    T unsigned(boolean unsigned);

    default T unsigned()
    {
        return unsigned(true);
    }
}
