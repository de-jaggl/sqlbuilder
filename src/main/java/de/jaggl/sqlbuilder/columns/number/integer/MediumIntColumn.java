package de.jaggl.sqlbuilder.columns.number.integer;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;

public class MediumIntColumn extends IntegerColumn<MediumIntColumn>
{
    public MediumIntColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition);
    }

    public MediumIntColumn as(String alias)
    {
        return new MediumIntColumn(table, name, alias, columnDefinition);
    }
}
