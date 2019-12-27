package de.jaggl.sqlbuilder.functions;

import de.jaggl.sqlbuilder.columns.Column;

public class Sum extends NumberColumnFunction
{
    public Sum(Column column)
    {
        super(column, "SUM");
    }

    public Sum(Column column, String alias)
    {
        super(column, "SUM", alias);
    }

    public Sum as(String alias)
    {
        return new Sum(column, alias);
    }
}
