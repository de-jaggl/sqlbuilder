package de.jaggl.sqlbuilder.core.columns.number.doubletype;

import de.jaggl.sqlbuilder.core.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.core.schema.Table;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
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
