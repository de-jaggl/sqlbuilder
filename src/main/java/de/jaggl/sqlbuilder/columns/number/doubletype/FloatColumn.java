package de.jaggl.sqlbuilder.columns.number.doubletype;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;

public class FloatColumn extends DoubleTypeColumn<FloatColumn>
{
    public FloatColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition);
    }

    public FloatColumn as(String alias)
    {
        return new FloatColumn(getTable(), getName(), alias, columnDefinition);
    }
}
