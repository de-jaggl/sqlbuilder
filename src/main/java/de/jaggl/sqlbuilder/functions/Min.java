package de.jaggl.sqlbuilder.functions;

import de.jaggl.sqlbuilder.columns.Column;

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
}