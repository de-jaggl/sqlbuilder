package de.jaggl.sqlbuilder.columns.number.integer;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.columns.number.NumberColumn;
import de.jaggl.sqlbuilder.schema.Table;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@ToString(callSuper = true)
public abstract class IntegerColumn<T extends IntegerColumn<T>> extends NumberColumn<T, Integer>
{
    public IntegerColumn(Table table, String name, String alias, ColumnDefinition columnDefinition, int sqlType)
    {
        super(table, name, alias, columnDefinition, sqlType);
    }
}
