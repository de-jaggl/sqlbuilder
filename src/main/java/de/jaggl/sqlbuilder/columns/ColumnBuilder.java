package de.jaggl.sqlbuilder.columns;

import static de.jaggl.sqlbuilder.schema.TableBuilderUtil.addColumnToTable;

import de.jaggl.sqlbuilder.schema.Table;
import lombok.RequiredArgsConstructor;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@RequiredArgsConstructor
public abstract class ColumnBuilder<C extends Column, B extends ColumnBuilder<C, B, V>, V>
{
    protected final Table table;
    protected final String name;

    protected boolean isNullable = true;
    protected boolean isDefaultNull = true;
    protected V defaultValue;

    protected abstract C getColumnInstance();

    public C build()
    {
        C columnInstance = getColumnInstance();
        addColumnToTable(columnInstance, table);
        return columnInstance;
    }

    @SuppressWarnings("unchecked")
    public B nullable(boolean nullable)
    {
        isNullable = nullable;
        return (B) this;
    }

    @SuppressWarnings("unchecked")
    public B defaultValue(V value)
    {
        isDefaultNull = false;
        defaultValue = value;
        return (B) this;
    }

    @SuppressWarnings("unchecked")
    public B defaultNull()
    {
        isNullable = true;
        defaultValue = null;
        isDefaultNull = true;
        return (B) this;
    }

    @SuppressWarnings("unchecked")
    public B noDefault()
    {
        defaultValue = null;
        isDefaultNull = false;
        return (B) this;
    }

    public B nullable()
    {
        return nullable(true);
    }

    public B notNull()
    {
        return nullable(false);
    }
}
