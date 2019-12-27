package de.jaggl.sqlbuilder.functions;

import de.jaggl.sqlbuilder.columns.Column;
import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.utils.Indentation;

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
