package de.jaggl.sqlbuilder.conditions;

import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_EQUAL_TO;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NOT_EQUAL_TO;

import de.jaggl.sqlbuilder.columns.Column;

public interface EqualityConditions
{
    default Condition isEqualTo(Column otherColumn)
    {
        return new GenericCondition(IS_EQUAL_TO, this, otherColumn);
    }

    /**
     * Alias for {@link #isEqualTo(Column)} for shorter statements
     *
     * @param otherColumn {@link Column} to compare equality against
     * @return the {@link Condition}
     */
    default Condition eq(Column otherColumn)
    {
        return isEqualTo(otherColumn);
    }

    default Condition isNotEqualTo(Column otherColumn)
    {
        return new GenericCondition(IS_NOT_EQUAL_TO, this, otherColumn);
    }

    /**
     * Alias for {@link #isNotEqualTo(Column)} for shorter statements
     *
     * @param otherColumn {@link Column} to compare equality against
     * @return the {@link Condition}
     */
    default Condition nEq(Column otherColumn)
    {
        return isNotEqualTo(otherColumn);
    }
}
