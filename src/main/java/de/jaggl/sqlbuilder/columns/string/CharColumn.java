package de.jaggl.sqlbuilder.columns.string;

import static java.sql.Types.CHAR;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@ToString(callSuper = true)
public class CharColumn extends StringColumn<CharColumn>
{
    CharColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition, CHAR);
    }

    public CharColumn as(String alias)
    {
        return new CharColumn(getTable(), getName(), alias, columnDefinition);
    }
}
