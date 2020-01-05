package de.jaggl.sqlbuilder.functions;

import de.jaggl.sqlbuilder.columns.Column;
import de.jaggl.sqlbuilder.conditions.EqualityConditions;
import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.utils.Indentation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public abstract class ColumnFunction implements Function, EqualityConditions
{
    protected final Column column;
    protected final String definition;

    @Getter
    private String alias;

    @Override
    public String getValue(BuildingContext context, Indentation indentation)
    {
        return definition + "(" + column.getFullNameOrAlias(context) + ")";
    }
}
