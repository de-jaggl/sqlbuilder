package com.avides.sqlbuilder.columns.string;

import static com.avides.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_EQUAL_TO;
import static com.avides.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NOT_EQUAL_TO;
import static com.avides.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NOT_NULL;
import static com.avides.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NULL;
import static com.avides.sqlbuilder.domain.LikeType.NONE;

import java.util.Collection;
import java.util.stream.Collectors;

import com.avides.sqlbuilder.columns.Column;
import com.avides.sqlbuilder.columns.ColumnDefinition;
import com.avides.sqlbuilder.conditions.Condition;
import com.avides.sqlbuilder.conditions.GenericCondition;
import com.avides.sqlbuilder.conditions.IsIn;
import com.avides.sqlbuilder.conditions.IsLike;
import com.avides.sqlbuilder.conditions.IsNotIn;
import com.avides.sqlbuilder.conditions.IsNotLike;
import com.avides.sqlbuilder.domain.LikeType;
import com.avides.sqlbuilder.schema.Table;
import com.avides.sqlbuilder.utils.ArrayUtils;

public abstract class StringColumn<T extends StringColumn<T>> extends Column
{
    public StringColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition);
    }

    public Condition isEqualTo(String value)
    {
        return value == null ? new GenericCondition(IS_NULL, this) : new GenericCondition(IS_EQUAL_TO, this, value);
    }

    public Condition isNotEqualTo(String value)
    {
        return value == null ? new GenericCondition(IS_NOT_NULL, this) : new GenericCondition(IS_NOT_EQUAL_TO, this, value);
    }

    public Condition isIn(Collection<CharSequence> values)
    {
        return new IsIn(this, values.stream().map(value -> (Object) value).collect(Collectors.toList()));
    }

    public Condition isIn(CharSequence value, CharSequence... furtherValues)
    {
        return new IsIn(this, ArrayUtils.toList(value, furtherValues));
    }

    public Condition isNotIn(Collection<CharSequence> values)
    {
        return new IsNotIn(this, values.stream().map(value -> (Object) value).collect(Collectors.toList()));
    }

    public Condition isNotIn(CharSequence value, CharSequence... furtherValues)
    {
        return new IsNotIn(this, ArrayUtils.toList(value, furtherValues));
    }

    public Condition isLike(CharSequence value)
    {
        return value == null ? new GenericCondition(IS_NULL, this) : new IsLike(this, value, NONE);
    }

    public Condition isLike(CharSequence value, LikeType likeType)
    {
        return value == null ? new GenericCondition(IS_NULL, this) : new IsLike(this, value, likeType);
    }

    public Condition isLike(Column otherColumn)
    {
        return new IsLike(this, otherColumn, NONE);
    }

    public Condition isLike(Column otherColumn, LikeType likeType)
    {
        return new IsLike(this, otherColumn, likeType);
    }

    public Condition isNotLike(CharSequence value)
    {
        return value == null ? new GenericCondition(IS_NOT_NULL, this) : new IsNotLike(this, value, NONE);
    }

    public Condition isNotLike(CharSequence value, LikeType likeType)
    {
        return value == null ? new GenericCondition(IS_NOT_NULL, this) : new IsNotLike(this, value, likeType);
    }

    public Condition isNotLike(Column otherColumn)
    {
        return new IsNotLike(this, otherColumn, NONE);
    }

    public Condition isNotLike(Column otherColumn, LikeType likeType)
    {
        return new IsNotLike(this, otherColumn, likeType);
    }
}
