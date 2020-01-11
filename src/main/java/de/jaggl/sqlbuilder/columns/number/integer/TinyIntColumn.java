package de.jaggl.sqlbuilder.columns.number.integer;

import static java.sql.Types.TINYINT;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@ToString(callSuper = true)
public class TinyIntColumn extends IntegerColumn<TinyIntColumn>
{
    public TinyIntColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition, TINYINT);
    }

    public TinyIntColumn as(String alias)
    {
        return new TinyIntColumn(table, name, alias, columnDefinition);
    }
}
