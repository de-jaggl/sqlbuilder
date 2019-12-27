package com.avides.sqlbuilder.columns;

import static com.avides.sqlbuilder.schema.TableBuilderUtil.addColumnToTable;

import com.avides.sqlbuilder.schema.Table;

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
