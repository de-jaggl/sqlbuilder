package de.jaggl.sqlbuilder.schema;

import de.jaggl.sqlbuilder.columns.Column;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public final class TableBuilderUtil
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
