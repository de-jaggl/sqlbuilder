package de.jaggl.sqlbuilder.columns;

import static de.jaggl.sqlbuilder.schema.TableBuilderUtil.addColumnToTable;

import de.jaggl.sqlbuilder.schema.Table;

public abstract class ColumnBuilder<T extends Column>
{
    protected final Table table;
    protected final String name;

    public ColumnBuilder(Table table, String name)
    {
        this.table = table;
        this.name = name;
    }

    protected abstract T getColumnInstance();

    public T build()
    {
        T columnInstance = getColumnInstance();
        addColumnToTable(columnInstance, table);
        return columnInstance;
    }
}
