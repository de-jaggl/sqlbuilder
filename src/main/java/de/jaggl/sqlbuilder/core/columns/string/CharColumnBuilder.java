package de.jaggl.sqlbuilder.core.columns.string;

import de.jaggl.sqlbuilder.core.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.core.domain.IntSize;
import de.jaggl.sqlbuilder.core.schema.Table;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class CharColumnBuilder extends StringColumnBuilder<CharColumnBuilder, CharColumn>
{
    private IntSize size;

    public CharColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    public CharColumnBuilder size(int value)
    {
        size = IntSize.valueOf(Integer.valueOf(value));
        return this;
    }

    public CharColumnBuilder size(Integer value)
    {
        size = IntSize.valueOf(value);
        return this;
    }

    @Override
    protected CharColumn getColumnInstance()
    {
        return new CharColumn(table, name, null, new ColumnDefinition("CHAR", size, isNullable, isDefaultNull, false, false, defaultValue));
    }
}
