package de.jaggl.sqlbuilder.columns.number.doubletype;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class DecimalColumn extends DoubleTypeColumn<DecimalColumn>
{
    public DecimalColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition);
    }

    public DecimalColumn as(String alias)
    {
        return new DecimalColumn(table, name, alias, columnDefinition);
    }
}
