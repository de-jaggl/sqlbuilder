package de.jaggl.sqlbuilder.columns.number.integer;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class BigIntColumn extends IntegerColumn<BigIntColumn>
{
    public BigIntColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition);
    }

    public BigIntColumn as(String alias)
    {
        return new BigIntColumn(table, name, alias, columnDefinition);
    }
}
