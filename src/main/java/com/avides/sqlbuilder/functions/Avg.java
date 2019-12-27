package com.avides.sqlbuilder.functions;

import com.avides.sqlbuilder.columns.Column;

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
