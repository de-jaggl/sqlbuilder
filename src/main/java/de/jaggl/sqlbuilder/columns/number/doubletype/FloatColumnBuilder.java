package de.jaggl.sqlbuilder.columns.number.doubletype;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;

public class FloatColumnBuilder extends DoubleTypeColumnBuilder<FloatColumnBuilder, FloatColumn>
{
    public FloatColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    @Override
    protected FloatColumn getColumnInstance()
    {
        return new FloatColumn(table, name, null, new ColumnDefinition("FLOAT", size, isNullable, isDefaultNull, isUnsigned, false, defaultValue));
    }
}
