package de.jaggl.sqlbuilder.core.columns.number.doubletype;

import de.jaggl.sqlbuilder.core.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.core.schema.Table;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class DecimalColumnBuilder extends DoubleTypeColumnBuilder<DecimalColumnBuilder, DecimalColumn>
{
    public DecimalColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    @Override
    protected DecimalColumn getColumnInstance()
    {
        return new DecimalColumn(table, name, null, new ColumnDefinition("DECIMAL", size, isNullable, isDefaultNull, isUnsigned, false, defaultValue));
    }
}
