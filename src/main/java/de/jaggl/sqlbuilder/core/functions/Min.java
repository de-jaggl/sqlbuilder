package de.jaggl.sqlbuilder.core.functions;

import de.jaggl.sqlbuilder.core.columns.Column;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@ToString(callSuper = true)
public class Min extends NumberColumnFunction
{
    public Min(Column column)
    {
        super(column, "MIN");
    }

    public Min(Column column, String alias)
    {
        super(column, "MIN", alias);
    }

    public Min as(String alias)
    {
        return new Min(column, alias);
    }

    @Override
    public int getSqlType()
    {
        return column.getSqlType();
    }
}
