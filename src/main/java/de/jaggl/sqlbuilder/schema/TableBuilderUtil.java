package de.jaggl.sqlbuilder.schema;

import de.jaggl.sqlbuilder.columns.Column;

public abstract class TableBuilderUtil
{
    private TableBuilderUtil()
    {
        // private constructor to hide the public one
    }

    public static void addColumnToTable(Column column, Table table)
    {
        table.addColumn(column);
    }
}
