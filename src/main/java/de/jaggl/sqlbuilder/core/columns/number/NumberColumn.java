package de.jaggl.sqlbuilder.core.columns.number;

import de.jaggl.sqlbuilder.core.columns.Column;
import de.jaggl.sqlbuilder.core.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.core.conditions.NumberConditions;
import de.jaggl.sqlbuilder.core.schema.Table;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@ToString(callSuper = true)
public abstract class NumberColumn<T extends NumberColumn<T, N>, N extends Number> extends Column implements NumberConditions
{
    public NumberColumn(Table table, String name, String alias, ColumnDefinition columnDefinition, int sqlType)
    {
        super(table, name, alias, columnDefinition, sqlType);
    }
}
