package de.jaggl.sqlbuilder.core.columns.string;

import de.jaggl.sqlbuilder.core.columns.Column;
import de.jaggl.sqlbuilder.core.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.core.conditions.StringConditions;
import de.jaggl.sqlbuilder.core.schema.Table;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@ToString(callSuper = true)
public abstract class StringColumn<T extends StringColumn<T>> extends Column implements StringConditions
{
    public StringColumn(Table table, String name, String alias, ColumnDefinition columnDefinition, int sqlType)
    {
        super(table, name, alias, columnDefinition, sqlType);
    }
}
