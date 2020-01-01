package de.jaggl.sqlbuilder.functions;

import de.jaggl.sqlbuilder.columns.Column;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class Max extends NumberColumnFunction
{
    public Max(Column column)
    {
        super(column, "MAX");
    }

    public Max(Column column, String alias)
    {
        super(column, "MAX", alias);
    }

    public Max as(String alias)
    {
        return new Max(column, alias);
    }
}
