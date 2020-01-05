package de.jaggl.sqlbuilder.functions;

import de.jaggl.sqlbuilder.columns.Column;
import de.jaggl.sqlbuilder.conditions.NumberConditions;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@ToString(callSuper = true)
public abstract class NumberColumnFunction extends ColumnFunction implements NumberConditions
{
    public NumberColumnFunction(Column column, String definition)
    {
        super(column, definition);
    }

    public NumberColumnFunction(Column column, String definition, String alias)
    {
        super(column, definition, alias);
    }
}
