package de.jaggl.sqlbuilder.columns.string;

import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_EQUAL_TO;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NOT_EQUAL_TO;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NOT_NULL;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NULL;
import static de.jaggl.sqlbuilder.domain.LikeType.NONE;

import java.util.Collection;
import java.util.stream.Collectors;

import de.jaggl.sqlbuilder.columns.Column;
import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.conditions.Condition;
import de.jaggl.sqlbuilder.conditions.GenericCondition;
import de.jaggl.sqlbuilder.conditions.IsIn;
import de.jaggl.sqlbuilder.conditions.IsLike;
import de.jaggl.sqlbuilder.conditions.IsNotIn;
import de.jaggl.sqlbuilder.conditions.IsNotLike;
import de.jaggl.sqlbuilder.domain.LikeType;
import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.utils.ArrayUtils;

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
