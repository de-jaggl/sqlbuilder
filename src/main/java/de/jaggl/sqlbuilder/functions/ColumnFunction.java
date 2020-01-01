package de.jaggl.sqlbuilder.functions;

import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_EQUAL_TO;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NOT_EQUAL_TO;

import de.jaggl.sqlbuilder.columns.Column;
import de.jaggl.sqlbuilder.conditions.GenericCondition;
import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.utils.Indentation;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public abstract class ColumnFunction implements Function
{
    protected Column column;
    protected String definition;

    private String alias;

    public ColumnFunction(Column column, String definition)
    {
        this.column = column;
        this.definition = definition;
    }

    public ColumnFunction(Column column, String definition, String alias)
    {
        this.column = column;
        this.definition = definition;
        this.alias = alias;
    }

    @Override
    public String getAlias()
    {
        return alias;
    }

    @Override
    public String getValue(BuildingContext context, Indentation indentation)
    {
        return definition + "(" + column.getFullNameOrAlias(context) + ")";
    }

    public GenericCondition isEqualTo(Column otherColumn)
    {
        return new GenericCondition(IS_EQUAL_TO, this, otherColumn);
    }

    public GenericCondition isNotEqualTo(Column otherColumn)
    {
        return new GenericCondition(IS_NOT_EQUAL_TO, this, otherColumn);
    }
}
