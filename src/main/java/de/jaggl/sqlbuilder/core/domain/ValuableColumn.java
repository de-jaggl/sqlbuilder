package de.jaggl.sqlbuilder.core.domain;

import de.jaggl.sqlbuilder.core.columns.Column;
import de.jaggl.sqlbuilder.core.utils.Indentation;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@AllArgsConstructor
@ToString
public class ValuableColumn implements Valuable
{
    private Column column;

    @Override
    public String getValue(BuildingContext context, Indentation indentation)
    {
        return column.getFullNameOrAlias(context);
    }

    public String getName()
    {
        return column.getName();
    }
}
