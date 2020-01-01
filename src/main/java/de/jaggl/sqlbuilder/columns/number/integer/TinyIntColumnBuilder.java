package de.jaggl.sqlbuilder.columns.number.integer;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class TinyIntColumnBuilder extends IntegerColumnBuilder<TinyIntColumnBuilder, TinyIntColumn>
{
    public TinyIntColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    @Override
    protected TinyIntColumn getColumnInstance()
    {
        return new TinyIntColumn(table, name, null, new ColumnDefinition("TINYINT", size, isNullable, isDefaultNull, isUnsigned, isAutoIncrement, defaultValue));
    }
}
