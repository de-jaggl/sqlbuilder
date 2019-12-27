package de.jaggl.sqlbuilder.columns.number.doubletype;

import de.jaggl.sqlbuilder.columns.number.NumberColumn;
import de.jaggl.sqlbuilder.columns.number.NumberColumnBuilder;
import de.jaggl.sqlbuilder.domain.DoubleSize;
import de.jaggl.sqlbuilder.schema.Table;

public abstract class DoubleTypeColumnBuilder<T extends DoubleTypeColumnBuilder<T, C>, C extends NumberColumn<C, Double>>
        extends NumberColumnBuilder<T, Double, C>
{
    protected DoubleSize size;

    public DoubleTypeColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    @SuppressWarnings("unchecked")
    public T defaultValue(double value)
    {
        defaultValue = Double.valueOf(value);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T size(double value)
    {
        this.size = DoubleSize.valueOf(Double.valueOf(value));
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T size(Double value)
    {
        this.size = DoubleSize.valueOf(value);
        return (T) this;
    }
}
