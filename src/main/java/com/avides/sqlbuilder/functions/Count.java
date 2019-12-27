package com.avides.sqlbuilder.functions;

import com.avides.sqlbuilder.columns.Column;
import com.avides.sqlbuilder.domain.BuildingContext;
import com.avides.sqlbuilder.utils.Indentation;

public class Count extends NumberColumnFunction
{
    public Count(Column column)
    {
        super(column, "COUNT");
    }

    public Count(Column column, String alias)
    {
        super(column, "COUNT", alias);
    }

    public Count as(String alias)
    {
        return new Count(column, alias);
    }

    @Override
    public String getValue(BuildingContext context, Indentation indentation)
    {
        return definition + "(" + (column != null ? column.getFullNameOrAlias(context) : "*") + ")";
    }
}
