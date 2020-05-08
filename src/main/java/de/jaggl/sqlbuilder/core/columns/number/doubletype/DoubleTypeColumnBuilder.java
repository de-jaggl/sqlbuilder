package de.jaggl.sqlbuilder.core.columns.number.doubletype;

import de.jaggl.sqlbuilder.core.columns.number.NumberColumn;
import de.jaggl.sqlbuilder.core.columns.number.NumberColumnBuilder;
import de.jaggl.sqlbuilder.core.domain.DoubleSize;
import de.jaggl.sqlbuilder.core.schema.Table;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public abstract class DoubleTypeColumnBuilder<T extends DoubleTypeColumnBuilder<T, C>, C extends NumberColumn<C, Double>>
        extends NumberColumnBuilder<T, Double, C>
{
    protected DoubleSize size;

    public DoubleTypeColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    public T defaultValue(double value)
    {
        return defaultValue(Double.valueOf(value));
    }

    @SuppressWarnings("unchecked")
    public T size(Double value)
    {
        this.size = DoubleSize.valueOf(value);
        return (T) this;
    }

    public T size(double value)
    {
        return size(Double.valueOf(value));
    }
}
