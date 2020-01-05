package de.jaggl.sqlbuilder.conditions;

import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_EQUAL_TO;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NOT_EQUAL_TO;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NOT_NULL;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NULL;
import static de.jaggl.sqlbuilder.domain.LikeType.NONE;

import java.util.Collection;
import java.util.stream.Collectors;

import de.jaggl.sqlbuilder.columns.Column;
import de.jaggl.sqlbuilder.domain.LikeType;
import de.jaggl.sqlbuilder.utils.ArrayUtils;

public interface StringConditions
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

    default Condition isLike(CharSequence value)
    {
        return value == null ? new GenericCondition(IS_NULL, this) : new IsLike(this, value, NONE);
    }

    default Condition isLike(CharSequence value, LikeType likeType)
    {
        return value == null ? new GenericCondition(IS_NULL, this) : new IsLike(this, value, likeType);
    }

    default Condition isLike(Column otherColumn)
    {
        return new IsLike(this, otherColumn, NONE);
    }

    default Condition isNotLike(CharSequence value)
    {
        return value == null ? new GenericCondition(IS_NOT_NULL, this) : new IsNotLike(this, value, NONE);
    }

    default Condition isNotLike(CharSequence value, LikeType likeType)
    {
        return value == null ? new GenericCondition(IS_NOT_NULL, this) : new IsNotLike(this, value, likeType);
    }

    default Condition isNotLike(Column otherColumn)
    {
        return new IsNotLike(this, otherColumn, NONE);
    }
}
