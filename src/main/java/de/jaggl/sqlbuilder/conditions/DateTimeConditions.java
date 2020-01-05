package de.jaggl.sqlbuilder.conditions;

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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.function.Function;

import de.jaggl.sqlbuilder.columns.Column;
import de.jaggl.sqlbuilder.domain.LikeType;

public interface DateTimeConditions
{
    Function<ZonedDateTime, Temporal> getDateConversion();

    default Condition isEqualTo(LocalDate value)
    {
        return value == null ? new GenericCondition(IS_NULL, this) : new GenericCondition(IS_EQUAL_TO, this, value);
    }

    default Condition eq(LocalDate value)
    {
        return isEqualTo(value);
    }

    default Condition isEqualTo(Date value)
    {
        return value == null ? new GenericCondition(IS_NULL, this) : new GenericCondition(IS_EQUAL_TO, this, convert(value, getDateConversion()));
    }

    default Condition eq(Date value)
    {
        return isEqualTo(value);
    }

    default Condition isNotEqualTo(LocalDate value)
    {
        return value == null ? new GenericCondition(IS_NOT_NULL, this) : new GenericCondition(IS_NOT_EQUAL_TO, this, value);
    }

    default Condition nEq(LocalDate value)
    {
        return isNotEqualTo(value);
    }

    default Condition isNotEqualTo(Date value)
    {
        return value == null ? new GenericCondition(IS_NOT_NULL, this) : new GenericCondition(IS_NOT_EQUAL_TO, this, convert(value, getDateConversion()));
    }

    default Condition nEq(Date value)
    {
        return isNotEqualTo(value);
    }

    default Condition isAfter(LocalDate value)
    {
        return new GenericCondition(IS_GREATER_THAN, this, value);
    }

    default Condition isAfter(Date value)
    {
        return new GenericCondition(IS_GREATER_THAN, this, convert(value, getDateConversion()));
    }

    default Condition isAfter(Column otherColumn)
    {
        return new GenericCondition(IS_GREATER_THAN, this, otherColumn);
    }

    default Condition isAfterOrEqualTo(LocalDate value)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, value);
    }

    default Condition isAfterOrEqualTo(Date value)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, convert(value, getDateConversion()));
    }

    default Condition isAfterOrEqualTo(Column otherColumn)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, otherColumn);
    }

    default Condition isBefore(LocalDate value)
    {
        return new GenericCondition(IS_LESS_THAN, this, value);
    }

    default Condition isBefore(Date value)
    {
        return new GenericCondition(IS_LESS_THAN, this, convert(value, getDateConversion()));
    }

    default Condition isBefore(Column otherColumn)
    {
        return new GenericCondition(IS_LESS_THAN, this, otherColumn);
    }

    default Condition isBeforeOrEqualTo(LocalDate value)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, value);
    }

    default Condition isBeforeOrEqualTo(Date value)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, convert(value, getDateConversion()));
    }

    default Condition isBeforeOrEqualTo(Column otherColumn)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, otherColumn);
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

    default Condition isBetween(LocalDate value1, LocalDate value2)
    {
        return new GenericCondition(IS_BETWEEN, this, value1, value2);
    }

    default Condition isBetween(Date value1, Date value2)
    {
        return new GenericCondition(IS_BETWEEN, this, convert(value1, getDateConversion()), convert(value2, getDateConversion()));
    }

    default Condition isBetween(LocalDate value1, Date value2)
    {
        return new GenericCondition(IS_BETWEEN, this, value1, convert(value2, getDateConversion()));
    }

    default Condition isBetween(Date value1, LocalDate value2)
    {
        return new GenericCondition(IS_BETWEEN, this, convert(value1, getDateConversion()), value2);
    }

    default Condition isBetween(Column otherColumn1, Column otherColumn2)
    {
        return new GenericCondition(IS_BETWEEN, this, otherColumn1, otherColumn2);
    }

    default Condition isBetween(LocalDate value, Column otherColumn)
    {
        return new GenericCondition(IS_BETWEEN, this, value, otherColumn);
    }

    default Condition isBetween(Date value, Column otherColumn)
    {
        return new GenericCondition(IS_BETWEEN, this, convert(value, getDateConversion()), otherColumn);
    }

    default Condition isBetween(Column otherColumn, LocalDate value)
    {
        return new GenericCondition(IS_BETWEEN, this, otherColumn, value);
    }

    default Condition isBetween(Column otherColumn, Date value)
    {
        return new GenericCondition(IS_BETWEEN, this, otherColumn, convert(value, getDateConversion()));
    }

    default GenericCondition isEqualTo(LocalDateTime value)
    {
        return value == null ? new GenericCondition(IS_NULL, this) : new GenericCondition(IS_EQUAL_TO, this, value);
    }

    default GenericCondition eq(LocalDateTime value)
    {
        return isEqualTo(value);
    }

    default GenericCondition isNotEqualTo(LocalDateTime value)
    {
        return value == null ? new GenericCondition(IS_NOT_NULL, this) : new GenericCondition(IS_NOT_EQUAL_TO, this, value);
    }

    default GenericCondition nEq(LocalDateTime value)
    {
        return isNotEqualTo(value);
    }

    default GenericCondition isAfter(LocalDateTime value)
    {
        return new GenericCondition(IS_GREATER_THAN, this, value);
    }

    default GenericCondition isAfterOrEqualTo(LocalDateTime value)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, value);
    }

    default GenericCondition isBefore(LocalDateTime value)
    {
        return new GenericCondition(IS_LESS_THAN, this, value);
    }

    default GenericCondition isBeforeOrEqualTo(LocalDateTime value)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, value);
    }

    default GenericCondition isBetween(LocalDateTime value1, LocalDateTime value2)
    {
        return new GenericCondition(IS_BETWEEN, this, value1, value2);
    }

    default GenericCondition isBetween(LocalDateTime value1, Date value2)
    {
        return new GenericCondition(IS_BETWEEN, this, value1, value2.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

    default GenericCondition isBetween(Date value1, LocalDateTime value2)
    {
        return new GenericCondition(IS_BETWEEN, this, value1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), value2);
    }

    default GenericCondition isBetween(LocalDateTime value, Column otherColumn)
    {
        return new GenericCondition(IS_BETWEEN, this, value, otherColumn);
    }

    default GenericCondition isBetween(Column otherColumn, LocalDateTime value)
    {
        return new GenericCondition(IS_BETWEEN, this, otherColumn, value);
    }

    private static Temporal convert(Date date, Function<ZonedDateTime, Temporal> convertFunction)
    {
        return convertFunction.apply(date.toInstant().atZone(ZoneId.systemDefault()));
    }
}
