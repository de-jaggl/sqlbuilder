package de.jaggl.sqlbuilder.core.columns.number.integer;

import de.jaggl.sqlbuilder.core.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.core.schema.Table;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
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
