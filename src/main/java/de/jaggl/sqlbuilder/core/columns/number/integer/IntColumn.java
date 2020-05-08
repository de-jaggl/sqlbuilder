package de.jaggl.sqlbuilder.core.columns.number.integer;

import static java.sql.Types.INTEGER;

import de.jaggl.sqlbuilder.core.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.core.schema.Table;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@ToString(callSuper = true)
public class IntColumn extends IntegerColumn<IntColumn>
{
    public IntColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition, INTEGER);
    }

    public IntColumn as(String alias)
    {
        return new IntColumn(table, name, alias, columnDefinition);
    }
}
