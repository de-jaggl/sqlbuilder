package de.jaggl.sqlbuilder.core.columns.string;

import de.jaggl.sqlbuilder.core.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.core.domain.IntSize;
import de.jaggl.sqlbuilder.core.schema.Table;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
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
