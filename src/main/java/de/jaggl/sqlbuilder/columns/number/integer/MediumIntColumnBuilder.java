package de.jaggl.sqlbuilder.columns.number.integer;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class MediumIntColumnBuilder extends IntegerColumnBuilder<MediumIntColumnBuilder, MediumIntColumn>
{
    public MediumIntColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    @Override
    protected MediumIntColumn getColumnInstance()
    {
        return new MediumIntColumn(table, name, null, new ColumnDefinition("MEDIUMINT", size, isNullable, isDefaultNull, isUnsigned, isAutoIncrement, defaultValue));
    }
}
