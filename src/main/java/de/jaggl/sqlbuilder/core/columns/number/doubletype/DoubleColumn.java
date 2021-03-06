package de.jaggl.sqlbuilder.core.columns.number.doubletype;

import static java.sql.Types.DOUBLE;

import de.jaggl.sqlbuilder.core.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.core.schema.Table;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@ToString(callSuper = true)
public class DoubleColumn extends DoubleTypeColumn<DoubleColumn>
{
    public DoubleColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition, DOUBLE);
    }

    public DoubleColumn as(String alias)
    {
        return new DoubleColumn(table, name, alias, columnDefinition);
    }
}
