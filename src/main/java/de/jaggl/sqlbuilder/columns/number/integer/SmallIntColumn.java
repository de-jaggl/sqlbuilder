package de.jaggl.sqlbuilder.columns.number.integer;

import static java.sql.Types.SMALLINT;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@ToString(callSuper = true)
public class SmallIntColumn extends IntegerColumn<SmallIntColumn>
{
    public SmallIntColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition, SMALLINT);
    }

    public SmallIntColumn as(String alias)
    {
        return new SmallIntColumn(table, name, alias, columnDefinition);
    }
}
