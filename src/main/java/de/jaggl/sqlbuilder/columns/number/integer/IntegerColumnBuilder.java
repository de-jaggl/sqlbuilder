package de.jaggl.sqlbuilder.columns.number.integer;

import de.jaggl.sqlbuilder.columns.configurable.AutoIncrementableColumnBuilder;
import de.jaggl.sqlbuilder.columns.number.NumberColumnBuilder;
import de.jaggl.sqlbuilder.domain.IntSize;
import de.jaggl.sqlbuilder.schema.Table;

public abstract class IntegerColumnBuilder<T extends IntegerColumnBuilder<T, C>, C extends IntegerColumn<C>>
        extends NumberColumnBuilder<T, Integer, C> implements AutoIncrementableColumnBuilder<T>
{
    protected boolean isAutoIncrement;
    protected IntSize size;

    public IntegerColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    @SuppressWarnings("unchecked")
    public T size(int value)
    {
        this.size = IntSize.valueOf(Integer.valueOf(value));
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T size(Integer value)
    {
        this.size = IntSize.valueOf(value);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T defaultValue(int value)
    {
        isDefaultNull = false;
        defaultValue = Integer.valueOf(value);
        return (T) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T autoIncrement(boolean autoIncrement)
    {
        isAutoIncrement = autoIncrement;
        return (T) this;
    }
}
