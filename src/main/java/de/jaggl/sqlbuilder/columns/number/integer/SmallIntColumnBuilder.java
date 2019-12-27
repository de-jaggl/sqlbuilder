package de.jaggl.sqlbuilder.columns.number.integer;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;

public class SmallIntColumnBuilder extends IntegerColumnBuilder<SmallIntColumnBuilder, SmallIntColumn>
{
    public SmallIntColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    @Override
    protected SmallIntColumn getColumnInstance()
    {
        return new SmallIntColumn(table, name, null, new ColumnDefinition("SMALLINT", size, isNullable, isDefaultNull, isUnsigned, isAutoIncrement, defaultValue));
    }
}
