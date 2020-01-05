package de.jaggl.sqlbuilder.columns;

import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_EQUAL_TO;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NOT_EQUAL_TO;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NOT_NULL;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NULL;

import de.jaggl.sqlbuilder.conditions.Condition;
import de.jaggl.sqlbuilder.conditions.GenericCondition;
import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.domain.Definable;
import de.jaggl.sqlbuilder.domain.Groupable;
import de.jaggl.sqlbuilder.domain.Selectable;
import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.utils.BuilderUtils;
import de.jaggl.sqlbuilder.utils.Indentation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@AllArgsConstructor
@Getter
@ToString
public abstract class Column implements Groupable, Selectable, Definable
{
    @ToString.Exclude
    protected Table table;
    protected String name;

    private String alias;

    protected ColumnDefinition columnDefinition;

    @Override
    public String getValue(BuildingContext context, Indentation indentation)
    {
        return getFullNameOrAlias(context);
    }

    public String getFullName(BuildingContext context)
    {
        return table.getFullName(context) + "." + BuilderUtils.columnApostrophe(name, context);
    }

    public String getFullNameOrAlias(BuildingContext context)
    {
        return table.getFullNameOrAlias(context) + "." + BuilderUtils.columnApostrophe(name, context);
    }

    public Condition isNull()
    {
        return new GenericCondition(IS_NULL, this);
    }

    public Condition isNotNull()
    {
        return new GenericCondition(IS_NOT_NULL, this);
    }

    public Condition isEqualTo(Column otherColumn)
    {
        return new GenericCondition(IS_EQUAL_TO, this, otherColumn);
    }

    /**
     * Alias for {@link #isEqualTo(Column)} for shorter statements
     *
     * @param otherColumn {@link Column} to compare equality against
     * @return the {@link Condition}
     */
    public Condition eq(Column otherColumn)
    {
        return isEqualTo(otherColumn);
    }

    public Condition isNotEqualTo(Column otherColumn)
    {
        return new GenericCondition(IS_NOT_EQUAL_TO, this, otherColumn);
    }

    /**
     * Alias for {@link #isNotEqualTo(Column)} for shorter statements
     *
     * @param otherColumn {@link Column} to compare equality against
     * @return the {@link Condition}
     */
    public Condition nEq(Column otherColumn)
    {
        return isNotEqualTo(otherColumn);
    }
}
