package de.jaggl.sqlbuilder.columns.number.integer;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;

public class SmallIntColumn extends IntegerColumn<SmallIntColumn>
{
    public SmallIntColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition);
    }

    public SmallIntColumn as(String alias)
    {
        return new SmallIntColumn(table, name, alias, columnDefinition);
    }
}
