package de.jaggl.sqlbuilder.columns.number.doubletype;

import static java.sql.Types.FLOAT;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@ToString(callSuper = true)
public class FloatColumn extends DoubleTypeColumn<FloatColumn>
{
    public FloatColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition, FLOAT);
    }

    public FloatColumn as(String alias)
    {
        return new FloatColumn(getTable(), getName(), alias, columnDefinition);
    }
}
