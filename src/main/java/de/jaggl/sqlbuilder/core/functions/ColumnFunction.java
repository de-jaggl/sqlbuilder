package de.jaggl.sqlbuilder.core.functions;

import de.jaggl.sqlbuilder.core.columns.Column;
import de.jaggl.sqlbuilder.core.conditions.EqualityConditions;
import de.jaggl.sqlbuilder.core.domain.BuildingContext;
import de.jaggl.sqlbuilder.core.utils.Indentation;
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
