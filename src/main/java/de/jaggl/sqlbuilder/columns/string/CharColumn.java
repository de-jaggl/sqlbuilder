package de.jaggl.sqlbuilder.columns.string;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class CharColumn extends StringColumn<CharColumn>
{
    CharColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition);
    }

    public CharColumn as(String alias)
    {
        return new CharColumn(getTable(), getName(), alias, columnDefinition);
    }
}
