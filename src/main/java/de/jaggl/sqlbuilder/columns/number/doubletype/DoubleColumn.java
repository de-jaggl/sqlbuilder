package de.jaggl.sqlbuilder.columns.number.doubletype;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;

public class DoubleColumn extends DoubleTypeColumn<DoubleColumn>
{
    public DoubleColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition);
    }

    public DoubleColumn as(String alias)
    {
        return new DoubleColumn(table, name, alias, columnDefinition);
    }
}
