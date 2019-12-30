package de.jaggl.sqlbuilder.columns.datetime;

import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_BETWEEN;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_EQUAL_TO;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_GREATER_THAN;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_GREATER_THAN_OR_EQUAL_TO;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_LESS_THAN;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_LESS_THAN_OR_EQUAL_TO;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NOT_EQUAL_TO;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NOT_NULL;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NULL;
import static de.jaggl.sqlbuilder.domain.LikeType.NONE;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import de.jaggl.sqlbuilder.columns.Column;
import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.conditions.Condition;
import de.jaggl.sqlbuilder.conditions.GenericCondition;
import de.jaggl.sqlbuilder.conditions.IsLike;
import de.jaggl.sqlbuilder.conditions.IsNotLike;
import de.jaggl.sqlbuilder.domain.LikeType;
import de.jaggl.sqlbuilder.schema.Table;

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
