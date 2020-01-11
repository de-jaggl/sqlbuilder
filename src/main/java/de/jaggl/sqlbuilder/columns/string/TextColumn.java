package de.jaggl.sqlbuilder.columns.string;

import static java.sql.Types.VARCHAR;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@ToString(callSuper = true)
public class TextColumn extends StringColumn<TextColumn>
{
    TextColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition, VARCHAR);
    }

    public TextColumn as(String alias)
    {
        return new TextColumn(getTable(), getName(), alias, columnDefinition);
    }
}
