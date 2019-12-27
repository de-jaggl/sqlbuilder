package de.jaggl.sqlbuilder.columns.string;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;

public class VarCharColumn extends StringColumn<VarCharColumn>
{
    VarCharColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition);
    }

    public VarCharColumn as(String alias)
    {
        return new VarCharColumn(getTable(), getName(), alias, columnDefinition);
    }
}
