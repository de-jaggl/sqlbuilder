package de.jaggl.sqlbuilder.conditions;

import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_EQUAL_TO;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NOT_EQUAL_TO;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NOT_NULL;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NULL;

import java.util.Collection;
import java.util.stream.Collectors;

import de.jaggl.sqlbuilder.utils.ArrayUtils;

public interface StringConditions extends LikeConditions
{
    default Condition isEqualTo(String value)
    {
        return value == null ? new GenericCondition(IS_NULL, this) : new GenericCondition(IS_EQUAL_TO, this, value);
    }

    /**
     * Alias for {@link #isEqualTo(String)} for shorter statements
     *
     * @param value the value to compare equality against
     * @return the {@link Condition}
     */
    default Condition eq(String value)
    {
        return isEqualTo(value);
    }

    default Condition isNotEqualTo(String value)
    {
        return value == null ? new GenericCondition(IS_NOT_NULL, this) : new GenericCondition(IS_NOT_EQUAL_TO, this, value);
    }

    /**
     * Alias for {@link #isNotEqualTo(String)} for shorter statements
     *
     * @param value the value to compare equality against
     * @return the {@link Condition}
     */
    default Condition nEq(String value)
    {
        return isNotEqualTo(value);
    }

    default Condition isIn(Collection<CharSequence> values)
    {
        return new IsIn(this, values.stream().map(value -> (Object) value).collect(Collectors.toList()));
    }

    default Condition isIn(CharSequence value, CharSequence... furtherValues)
    {
        return new IsIn(this, ArrayUtils.toList(value, furtherValues));
    }

    default Condition isNotIn(Collection<CharSequence> values)
    {
        return new IsNotIn(this, values.stream().map(value -> (Object) value).collect(Collectors.toList()));
    }

    default Condition isNotIn(CharSequence value, CharSequence... furtherValues)
    {
        return new IsNotIn(this, ArrayUtils.toList(value, furtherValues));
    }
}
