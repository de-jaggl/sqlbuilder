package com.avides.sqlbuilder.columns.datetime;

import static com.avides.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_BETWEEN;
import static com.avides.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_EQUAL_TO;
import static com.avides.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_GREATER_THAN;
import static com.avides.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_GREATER_THAN_OR_EQUAL_TO;
import static com.avides.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_LESS_THAN;
import static com.avides.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_LESS_THAN_OR_EQUAL_TO;
import static com.avides.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NOT_EQUAL_TO;
import static com.avides.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NOT_NULL;
import static com.avides.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NULL;
import static com.avides.sqlbuilder.domain.LikeType.NONE;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.avides.sqlbuilder.columns.Column;
import com.avides.sqlbuilder.columns.ColumnDefinition;
import com.avides.sqlbuilder.conditions.Condition;
import com.avides.sqlbuilder.conditions.GenericCondition;
import com.avides.sqlbuilder.conditions.IsLike;
import com.avides.sqlbuilder.conditions.IsNotLike;
import com.avides.sqlbuilder.domain.LikeType;
import com.avides.sqlbuilder.schema.Table;

public class DateTimeColumn extends Column
{
    public DateTimeColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition);
    }

    public DateTimeColumn as(String alias)
    {
        return new DateTimeColumn(table, name, alias, columnDefinition);
    }

    @Override
    public ColumnDefinition getColumnDefinition()
    {
        return columnDefinition;
    }

    public GenericCondition isEqualTo(LocalDateTime value)
    {
        return value == null ? new GenericCondition(IS_NULL, this) : new GenericCondition(IS_EQUAL_TO, this, value);
    }

    public GenericCondition isEqualTo(Date value)
    {
        return value == null ? new GenericCondition(IS_NULL, this) : new GenericCondition(IS_EQUAL_TO, this, value
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());
    }

    public GenericCondition isNotEqualTo(LocalDateTime value)
    {
        return value == null ? new GenericCondition(IS_NOT_NULL, this) : new GenericCondition(IS_NOT_EQUAL_TO, this, value);
    }

    public GenericCondition isNotEqualTo(Date value)
    {
        return value == null ? new GenericCondition(IS_NOT_NULL, this) : new GenericCondition(IS_NOT_EQUAL_TO, this, value
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());
    }

    public GenericCondition isAfter(LocalDateTime value)
    {
        return new GenericCondition(IS_GREATER_THAN, this, value);
    }

    public GenericCondition isAfter(Date value)
    {
        return new GenericCondition(IS_GREATER_THAN, this, value.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

    public GenericCondition isAfter(Column otherColumn)
    {
        return new GenericCondition(IS_GREATER_THAN, this, otherColumn);
    }

    public GenericCondition isAfterOrEqualTo(LocalDateTime value)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, value);
    }

    public GenericCondition isAfterOrEqualTo(Date value)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, value.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

    public GenericCondition isAfterOrEqualTo(Column otherColumn)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, otherColumn);
    }

    public GenericCondition isBefore(LocalDateTime value)
    {
        return new GenericCondition(IS_LESS_THAN, this, value);
    }

    public GenericCondition isBefore(Date value)
    {
        return new GenericCondition(IS_LESS_THAN, this, value.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

    public GenericCondition isBefore(Column otherColumn)
    {
        return new GenericCondition(IS_LESS_THAN, this, otherColumn);
    }

    public GenericCondition isBeforeOrEqualTo(LocalDateTime value)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, value);
    }

    public GenericCondition isBeforeOrEqualTo(Date value)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, value.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

    public GenericCondition isBeforeOrEqualTo(Column otherColumn)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, otherColumn);
    }

    public Condition isLike(CharSequence value)
    {
        return value == null ? new GenericCondition(IS_NULL, this) : new IsLike(this, value, NONE);
    }

    public Condition isLike(CharSequence value, LikeType likeType)
    {
        return value == null ? new GenericCondition(IS_NULL, this) : new IsLike(this, value, likeType);
    }

    public IsLike isLike(Column otherColumn)
    {
        return new IsLike(this, otherColumn, NONE);
    }

    public IsLike isLike(Column otherColumn, LikeType likeType)
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

    public GenericCondition isBetween(LocalDateTime value1, LocalDateTime value2)
    {
        return new GenericCondition(IS_BETWEEN, this, value1, value2);
    }

    public GenericCondition isBetween(Date value1, Date value2)
    {
        return new GenericCondition(IS_BETWEEN, this, value1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), value2
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());
    }

    public GenericCondition isBetween(LocalDateTime value1, Date value2)
    {
        return new GenericCondition(IS_BETWEEN, this, value1, value2.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

    public GenericCondition isBetween(Date value1, LocalDateTime value2)
    {
        return new GenericCondition(IS_BETWEEN, this, value1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), value2);
    }

    public GenericCondition isBetween(Column otherColumn1, Column otherColumn2)
    {
        return new GenericCondition(IS_BETWEEN, this, otherColumn1, otherColumn2);
    }

    public GenericCondition isBetween(LocalDateTime value, Column otherColumn)
    {
        return new GenericCondition(IS_BETWEEN, this, value, otherColumn);
    }

    public GenericCondition isBetween(Date value, Column otherColumn)
    {
        return new GenericCondition(IS_BETWEEN, this, value.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), otherColumn);
    }

    public GenericCondition isBetween(Column otherColumn, LocalDateTime value)
    {
        return new GenericCondition(IS_BETWEEN, this, otherColumn, value);
    }

    public GenericCondition isBetween(Column otherColumn, Date value)
    {
        return new GenericCondition(IS_BETWEEN, this, otherColumn, value.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }
}
