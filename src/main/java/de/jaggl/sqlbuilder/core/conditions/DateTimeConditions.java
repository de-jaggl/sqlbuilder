package de.jaggl.sqlbuilder.core.conditions;

import static de.jaggl.sqlbuilder.core.conditions.GenericCondition.GenericConditionType.IS_BETWEEN;
import static de.jaggl.sqlbuilder.core.conditions.GenericCondition.GenericConditionType.IS_EQUAL_TO;
import static de.jaggl.sqlbuilder.core.conditions.GenericCondition.GenericConditionType.IS_GREATER_THAN;
import static de.jaggl.sqlbuilder.core.conditions.GenericCondition.GenericConditionType.IS_GREATER_THAN_OR_EQUAL_TO;
import static de.jaggl.sqlbuilder.core.conditions.GenericCondition.GenericConditionType.IS_LESS_THAN;
import static de.jaggl.sqlbuilder.core.conditions.GenericCondition.GenericConditionType.IS_LESS_THAN_OR_EQUAL_TO;
import static de.jaggl.sqlbuilder.core.conditions.GenericCondition.GenericConditionType.IS_NOT_EQUAL_TO;
import static de.jaggl.sqlbuilder.core.conditions.GenericCondition.GenericConditionType.IS_NOT_NULL;
import static de.jaggl.sqlbuilder.core.conditions.GenericCondition.GenericConditionType.IS_NULL;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.function.Function;

import de.jaggl.sqlbuilder.core.columns.Column;
import de.jaggl.sqlbuilder.core.domain.Placeholder;

/**
 * @author Martin Schumacher
 *
 * @since 2.1.0
 */
public interface DateTimeConditions extends LikeConditions
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

    default Condition isEqualTo(LocalDateTime value)
    {
        return value == null ? new GenericCondition(IS_NULL, this) : new GenericCondition(IS_EQUAL_TO, this, value);
    }

    default Condition eq(LocalDateTime value)
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

    default Condition isNotEqualTo(LocalDateTime value)
    {
        return value == null ? new GenericCondition(IS_NOT_NULL, this) : new GenericCondition(IS_NOT_EQUAL_TO, this, value);
    }

    default Condition nEq(LocalDateTime value)
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

    default Condition isAfter(LocalDateTime value)
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

    default Condition isAfter(Placeholder placeholder)
    {
        return new GenericCondition(IS_GREATER_THAN, this, placeholder);
    }

    default Condition isAfterOrEqualTo(LocalDate value)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, value);
    }

    default Condition isAfterOrEqualTo(LocalDateTime value)
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

    default Condition isAfterOrEqualTo(Placeholder placeholder)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, placeholder);
    }

    default Condition isBefore(LocalDate value)
    {
        return new GenericCondition(IS_LESS_THAN, this, value);
    }

    default Condition isBefore(LocalDateTime value)
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

    default Condition isBefore(Placeholder placeholder)
    {
        return new GenericCondition(IS_LESS_THAN, this, placeholder);
    }

    default Condition isBeforeOrEqualTo(LocalDate value)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, value);
    }

    default Condition isBeforeOrEqualTo(LocalDateTime value)
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

    default Condition isBeforeOrEqualTo(Placeholder placeholder)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, placeholder);
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

    default Condition isBetween(LocalDateTime value1, LocalDateTime value2)
    {
        return new GenericCondition(IS_BETWEEN, this, value1, value2);
    }

    default Condition isBetween(LocalDateTime value1, Date value2)
    {
        return new GenericCondition(IS_BETWEEN, this, value1, value2.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

    default Condition isBetween(Date value1, LocalDateTime value2)
    {
        return new GenericCondition(IS_BETWEEN, this, value1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), value2);
    }

    default Condition isBetween(LocalDateTime value, Column otherColumn)
    {
        return new GenericCondition(IS_BETWEEN, this, value, otherColumn);
    }

    default Condition isBetween(Column otherColumn, LocalDateTime value)
    {
        return new GenericCondition(IS_BETWEEN, this, otherColumn, value);
    }

    default Condition isBetween(Column otherColumn, Placeholder placeholder)
    {
        return new GenericCondition(IS_BETWEEN, this, otherColumn, placeholder);
    }

    default Condition isBetween(LocalDate value, Placeholder placeholder)
    {
        return new GenericCondition(IS_BETWEEN, this, value, placeholder);
    }

    default Condition isBetween(Date value, Placeholder placeholder)
    {
        return new GenericCondition(IS_BETWEEN, this, convert(value, getDateConversion()), placeholder);
    }

    default Condition isBetween(Placeholder placeholder, Date value)
    {
        return new GenericCondition(IS_BETWEEN, this, placeholder, convert(value, getDateConversion()));
    }

    default Condition isBetween(Placeholder placeholder, LocalDate value)
    {
        return new GenericCondition(IS_BETWEEN, this, placeholder, value);
    }

    default Condition isBetween(Placeholder placeholder, Column otherColumn)
    {
        return new GenericCondition(IS_BETWEEN, this, placeholder, otherColumn);
    }

    default Condition isBetween(LocalDateTime value, Placeholder placeholder)
    {
        return new GenericCondition(IS_BETWEEN, this, value, placeholder);
    }

    default Condition isBetween(Placeholder placeholder, LocalDateTime value)
    {
        return new GenericCondition(IS_BETWEEN, this, placeholder, value);
    }

    private static Temporal convert(Date date, Function<ZonedDateTime, Temporal> convertFunction)
    {
        return convertFunction.apply(date.toInstant().atZone(ZoneId.systemDefault()));
    }
}
