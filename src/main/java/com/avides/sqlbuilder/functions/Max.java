package com.avides.sqlbuilder.functions;

import com.avides.sqlbuilder.columns.Column;

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
