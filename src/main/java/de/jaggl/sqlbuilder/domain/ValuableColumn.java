package de.jaggl.sqlbuilder.domain;

import de.jaggl.sqlbuilder.columns.Column;
import de.jaggl.sqlbuilder.utils.Indentation;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class ValuableColumn implements Valuable
{
    private Column column;

    public ValuableColumn(Column column)
    {
        this.column = column;
    }

    @Override
    public String getValue(BuildingContext context, Indentation indentation)
    {
        return column.getFullNameOrAlias(context);
    }
}
