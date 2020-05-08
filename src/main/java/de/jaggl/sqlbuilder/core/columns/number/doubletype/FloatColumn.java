package de.jaggl.sqlbuilder.core.columns.number.doubletype;

import static java.sql.Types.FLOAT;

import de.jaggl.sqlbuilder.core.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.core.schema.Table;
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
