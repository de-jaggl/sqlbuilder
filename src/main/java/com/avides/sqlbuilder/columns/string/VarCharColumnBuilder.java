package com.avides.sqlbuilder.columns.string;

import com.avides.sqlbuilder.columns.ColumnDefinition;
import com.avides.sqlbuilder.domain.IntSize;
import com.avides.sqlbuilder.schema.Table;

public class VarCharColumnBuilder extends StringColumnBuilder<VarCharColumnBuilder, VarCharColumn>
{
    private IntSize size;

    public VarCharColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    public VarCharColumnBuilder size(int value)
    {
        size = IntSize.valueOf(Integer.valueOf(value));
        return this;
    }

    public VarCharColumnBuilder size(Integer value)
    {
        size = IntSize.valueOf(value);
        return this;
    }

    @Override
    protected VarCharColumn getColumnInstance()
    {
        return new VarCharColumn(table, name, null, new ColumnDefinition("VARCHAR", size, isNullable, isDefaultNull, false, false, defaultValue));
    }
}
