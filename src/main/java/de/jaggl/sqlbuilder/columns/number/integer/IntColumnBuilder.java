package de.jaggl.sqlbuilder.columns.number.integer;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class IntColumnBuilder extends IntegerColumnBuilder<IntColumnBuilder, IntColumn>
{
    public IntColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    @Override
    protected IntColumn getColumnInstance()
    {
        return new IntColumn(table, name, null, new ColumnDefinition("INT", size, isNullable, isDefaultNull, isUnsigned, isAutoIncrement, defaultValue));
    }
}
