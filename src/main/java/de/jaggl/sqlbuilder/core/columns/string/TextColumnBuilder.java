package de.jaggl.sqlbuilder.core.columns.string;

import de.jaggl.sqlbuilder.core.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.core.schema.Table;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class TextColumnBuilder extends StringColumnBuilder<TextColumnBuilder, TextColumn>
{
    public TextColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    @Override
    protected TextColumn getColumnInstance()
    {
        return new TextColumn(table, name, null, new ColumnDefinition("TEXT", null, isNullable, isDefaultNull, false, false, defaultValue));
    }
}
