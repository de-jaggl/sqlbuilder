package de.jaggl.sqlbuilder.core.columns.number.integer;

import de.jaggl.sqlbuilder.core.columns.number.NumberColumnBuilder;
import de.jaggl.sqlbuilder.core.domain.IntSize;
import de.jaggl.sqlbuilder.core.schema.Table;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public abstract class IntegerColumnBuilder<T extends IntegerColumnBuilder<T, C>, C extends IntegerColumn<C>>
        extends NumberColumnBuilder<T, Integer, C>
{
    protected boolean isAutoIncrement;
    protected IntSize size;

    public IntegerColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    @SuppressWarnings("unchecked")
    public T size(Integer value)
    {
        this.size = IntSize.valueOf(value);
        return (T) this;
    }

    public T size(int value)
    {
        return size(Integer.valueOf(value));
    }

    public T defaultValue(int value)
    {
        return defaultValue(Integer.valueOf(value));
    }

    @SuppressWarnings("unchecked")
    public T autoIncrement(boolean autoIncrement)
    {
        isAutoIncrement = autoIncrement;
        return (T) this;
    }

    public T autoIncrement()
    {
        return autoIncrement(true);
    }
}
