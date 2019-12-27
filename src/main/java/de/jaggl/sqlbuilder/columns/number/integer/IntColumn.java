package de.jaggl.sqlbuilder.columns.number.integer;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;

public class IntColumn extends IntegerColumn<IntColumn>
{
    public IntColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition);
    }

    public IntColumn as(String alias)
    {
        return new IntColumn(table, name, alias, columnDefinition);
    }
}