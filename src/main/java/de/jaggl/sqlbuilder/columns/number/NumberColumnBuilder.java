package de.jaggl.sqlbuilder.columns.number;

import de.jaggl.sqlbuilder.columns.ColumnBuilder;
import de.jaggl.sqlbuilder.schema.Table;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public abstract class NumberColumnBuilder<T extends NumberColumnBuilder<T, N, C>, N extends Number, C extends NumberColumn<C, N>> extends ColumnBuilder<C, T, N>
{
    protected boolean isUnsigned;

    public NumberColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    @SuppressWarnings("unchecked")
    public T unsigned(boolean unsigned)
    {
        isUnsigned = unsigned;
        return (T) this;
    }

    public T unsigned()
    {
        return unsigned(true);
    }
}
