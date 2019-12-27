package de.jaggl.sqlbuilder.functions;

import de.jaggl.sqlbuilder.columns.Column;
import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.domain.Selectable;
import de.jaggl.sqlbuilder.utils.Indentation;

public interface Function extends Selectable
{
    @Override
    String getValue(BuildingContext context, Indentation indentation);

    public static Sum sum(Column column)
    {
        return new Sum(column);
    }

    public static Min min(Column column)
    {
        return new Min(column);
    }

    public static Max max(Column column)
    {
        return new Max(column);
    }

    public static Avg avg(Column column)
    {
        return new Avg(column);
    }

    public static Count count(Column column)
    {
        return new Count(column);
    }

    public static Count count()
    {
        return new Count(null);
    }

    public static Now now()
    {
        return new Now();
    }
}
