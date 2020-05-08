package de.jaggl.sqlbuilder.core.columns.number.doubletype;

import static java.sql.Types.DECIMAL;

import de.jaggl.sqlbuilder.core.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.core.schema.Table;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@ToString(callSuper = true)
public class DecimalColumn extends DoubleTypeColumn<DecimalColumn>
{
    public DecimalColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition, DECIMAL);
    }

    public DecimalColumn as(String alias)
    {
        return new DecimalColumn(table, name, alias, columnDefinition);
    }
}
