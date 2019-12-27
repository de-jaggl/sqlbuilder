package com.avides.sqlbuilder.columns.number.doubletype;

import com.avides.sqlbuilder.columns.ColumnDefinition;
import com.avides.sqlbuilder.schema.Table;

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
