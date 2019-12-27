package de.jaggl.sqlbuilder.columns.number.integer;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;

public class TinyIntColumn extends IntegerColumn<TinyIntColumn>
{
    public TinyIntColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition);
    }

    public TinyIntColumn as(String alias)
    {
        return new TinyIntColumn(table, name, alias, columnDefinition);
    }
}
