package de.jaggl.sqlbuilder.columns.string;

import de.jaggl.sqlbuilder.columns.Column;
import de.jaggl.sqlbuilder.columns.ColumnBuilder;
import de.jaggl.sqlbuilder.schema.Table;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public abstract class StringColumnBuilder<T extends StringColumnBuilder<T, C>, C extends Column> extends ColumnBuilder<C, T, CharSequence>
{
    public StringColumnBuilder(Table table, String name)
    {
        super(table, name);
    }
}
