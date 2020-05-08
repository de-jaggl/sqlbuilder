package de.jaggl.sqlbuilder.core.functions;

import de.jaggl.sqlbuilder.core.columns.Column;
import de.jaggl.sqlbuilder.core.domain.BuildingContext;
import de.jaggl.sqlbuilder.core.domain.Selectable;
import de.jaggl.sqlbuilder.core.domain.SqlTypeSupplier;
import de.jaggl.sqlbuilder.core.utils.Indentation;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public interface Function extends Selectable, SqlTypeSupplier
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
