package de.jaggl.sqlbuilder.core.conditions;

import static de.jaggl.sqlbuilder.core.conditions.GenericCondition.GenericConditionType.IS_EQUAL_TO;
import static de.jaggl.sqlbuilder.core.conditions.GenericCondition.GenericConditionType.IS_NOT_EQUAL_TO;

import de.jaggl.sqlbuilder.core.columns.Column;
import de.jaggl.sqlbuilder.core.domain.Placeholder;

/**
 * @author Martin Schumacher
 *
 * @since 2.1.0
 */
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

    default Condition isEqualTo(Placeholder placeholder)
    {
        return new GenericCondition(IS_EQUAL_TO, this, placeholder);
    }

    default Condition eq(Placeholder placeholder)
    {
        return isEqualTo(placeholder);
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

    default Condition isNotEqualTo(Placeholder placeholder)
    {
        return new GenericCondition(IS_NOT_EQUAL_TO, this, placeholder);
    }

    default Condition nEq(Placeholder placeholder)
    {
        return isNotEqualTo(placeholder);
    }
}
