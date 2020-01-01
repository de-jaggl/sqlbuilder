package de.jaggl.sqlbuilder.columns.configurable;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public interface AutoIncrementableColumnBuilder<T extends AutoIncrementableColumnBuilder<T>>
{
    T autoIncrement(boolean autoIncrement);

    default T autoIncrement()
    {
        return autoIncrement(true);
    }
}
