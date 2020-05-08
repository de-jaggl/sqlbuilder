package de.jaggl.sqlbuilder.core.functions;

import static java.sql.Types.INTEGER;

import de.jaggl.sqlbuilder.core.columns.Column;
import de.jaggl.sqlbuilder.core.domain.BuildingContext;
import de.jaggl.sqlbuilder.core.utils.Indentation;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@ToString(callSuper = true)
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

    @Override
    public int getSqlType()
    {
        return INTEGER;
    }
}
