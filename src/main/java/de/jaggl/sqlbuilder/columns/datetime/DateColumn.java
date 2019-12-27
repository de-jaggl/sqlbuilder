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

import java.time.LocalDate;
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

public class DateColumn extends Column
{
    public DateColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition);
    }

    public DateColumn as(String alias)
    {
        return new DateColumn(table, name, alias, columnDefinition);
    }

    @Override
    public ColumnDefinition getColumnDefinition()
    {
        return columnDefinition;
    }

    public Condition isEqualTo(LocalDate value)
    {
        return value == null ? new GenericCondition(IS_NULL, this) : new GenericCondition(IS_EQUAL_TO, this, value);
    }

    public Condition isEqualTo(Date value)
    {
        return value == null ? new GenericCondition(IS_NULL, this) : new GenericCondition(IS_EQUAL_TO, this, value
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
    }

    public Condition isNotEqualTo(LocalDate value)
    {
        return value == null ? new GenericCondition(IS_NOT_NULL, this) : new GenericCondition(IS_NOT_EQUAL_TO, this, value);
    }

    public Condition isNotEqualTo(Date value)
    {
        return value == null ? new GenericCondition(IS_NOT_NULL, this) : new GenericCondition(IS_NOT_EQUAL_TO, this, value
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
    }

    public Condition isAfter(LocalDate value)
    {
        return new GenericCondition(IS_GREATER_THAN, this, value);
    }

    public Condition isAfter(Date value)
    {
        return new GenericCondition(IS_GREATER_THAN, this, value.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    public Condition isAfter(Column otherColumn)
    {
        return new GenericCondition(IS_GREATER_THAN, this, otherColumn);
    }

    public Condition isAfterOrEqualTo(LocalDate value)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, value);
    }

    public Condition isAfterOrEqualTo(Date value)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, value.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    public Condition isAfterOrEqualTo(Column otherColumn)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, otherColumn);
    }

    public Condition isBefore(LocalDate value)
    {
        return new GenericCondition(IS_LESS_THAN, this, value);
    }

    public Condition isBefore(Date value)
    {
        return new GenericCondition(IS_LESS_THAN, this, value.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    public Condition isBefore(Column otherColumn)
    {
        return new GenericCondition(IS_LESS_THAN, this, otherColumn);
    }

    public Condition isBeforeOrEqualTo(LocalDate value)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, value);
    }

    public Condition isBeforeOrEqualTo(Date value)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, value.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    public Condition isBeforeOrEqualTo(Column otherColumn)
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

    public Condition isBetween(LocalDate value1, LocalDate value2)
    {
        return new GenericCondition(IS_BETWEEN, this, value1, value2);
    }

    public Condition isBetween(Date value1, Date value2)
    {
        return new GenericCondition(IS_BETWEEN, this, value1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), value2.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
    }

    public Condition isBetween(LocalDate value1, Date value2)
    {
        return new GenericCondition(IS_BETWEEN, this, value1, value2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    public Condition isBetween(Date value1, LocalDate value2)
    {
        return new GenericCondition(IS_BETWEEN, this, value1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), value2);
    }

    public Condition isBetween(Column otherColumn1, Column otherColumn2)
    {
        return new GenericCondition(IS_BETWEEN, this, otherColumn1, otherColumn2);
    }

    public Condition isBetween(LocalDate value, Column otherColumn)
    {
        return new GenericCondition(IS_BETWEEN, this, value, otherColumn);
    }

    public Condition isBetween(Date value, Column otherColumn)
    {
        return new GenericCondition(IS_BETWEEN, this, value.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), otherColumn);
    }

    public Condition isBetween(Column otherColumn, LocalDate value)
    {
        return new GenericCondition(IS_BETWEEN, this, otherColumn, value);
    }

    public Condition isBetween(Column otherColumn, Date value)
    {
        return new GenericCondition(IS_BETWEEN, this, otherColumn, value.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
}
