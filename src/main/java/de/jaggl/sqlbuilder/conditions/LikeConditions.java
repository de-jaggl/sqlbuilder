package de.jaggl.sqlbuilder.conditions;

import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NOT_NULL;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NULL;
import static de.jaggl.sqlbuilder.domain.LikeType.NONE;

import de.jaggl.sqlbuilder.columns.Column;
import de.jaggl.sqlbuilder.domain.LikeType;
import de.jaggl.sqlbuilder.domain.Placeholder;

public interface LikeConditions
{
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

    default Condition isLike(Placeholder placeholder)
    {
        return new IsLike(this, placeholder, NONE);
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

    default Condition isNotLike(Placeholder placeholder)
    {
        return new IsNotLike(this, placeholder, NONE);
    }
}
