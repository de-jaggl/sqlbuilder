package de.jaggl.sqlbuilder.columns.number.integer;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class BigIntColumnBuilder extends IntegerColumnBuilder<BigIntColumnBuilder, BigIntColumn>
{
    public BigIntColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    @Override
    protected BigIntColumn getColumnInstance()
    {
        return new BigIntColumn(table, name, null, new ColumnDefinition("BIGINT", size, isNullable, isDefaultNull, isUnsigned, isAutoIncrement, defaultValue));
    }
}
