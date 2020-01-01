package de.jaggl.sqlbuilder.functions;

import de.jaggl.sqlbuilder.columns.Column;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class Avg extends NumberColumnFunction
{
    public Avg(Column column)
    {
        super(column, "AVG");
    }

    public Avg(Column column, String alias)
    {
        super(column, "AVG", alias);
    }

    public Avg as(String alias)
    {
        return new Avg(column, alias);
    }
}
