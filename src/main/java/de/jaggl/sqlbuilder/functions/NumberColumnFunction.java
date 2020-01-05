package de.jaggl.sqlbuilder.functions;

import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_BETWEEN;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_EQUAL_TO;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_GREATER_THAN;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_GREATER_THAN_OR_EQUAL_TO;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_LESS_THAN;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_LESS_THAN_OR_EQUAL_TO;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NOT_EQUAL_TO;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NOT_NULL;
import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_NULL;

import de.jaggl.sqlbuilder.columns.Column;
import de.jaggl.sqlbuilder.columns.number.NumberColumn;
import de.jaggl.sqlbuilder.conditions.Condition;
import de.jaggl.sqlbuilder.conditions.GenericCondition;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@ToString(callSuper = true)
public abstract class NumberColumnFunction extends ColumnFunction
{
    public NumberColumnFunction(Column column, String definition)
    {
        super(column, definition);
    }

    public NumberColumnFunction(Column column, String definition, String alias)
    {
        super(column, definition, alias);
    }

    public Condition isEqualTo(Number value)
    {
        return value == null ? new GenericCondition(IS_NULL, this) : new GenericCondition(IS_EQUAL_TO, this, value);
    }

    public Condition eq(Number value)
    {
        return isEqualTo(value);
    }

    public Condition isEqualTo(long value)
    {
        return new GenericCondition(IS_EQUAL_TO, this, Long.valueOf(value));
    }

    public Condition eq(long value)
    {
        return isEqualTo(value);
    }

    public Condition isEqualTo(double value)
    {
        return new GenericCondition(IS_EQUAL_TO, this, Double.valueOf(value));
    }

    public Condition eq(double value)
    {
        return isEqualTo(value);
    }

    public Condition isNotEqualTo(Number value)
    {
        return value == null ? new GenericCondition(IS_NOT_NULL, this) : new GenericCondition(IS_NOT_EQUAL_TO, this, value);
    }

    public Condition nEq(Number value)
    {
        return isNotEqualTo(value);
    }

    public Condition isNotEqualTo(long value)
    {
        return new GenericCondition(IS_NOT_EQUAL_TO, this, Long.valueOf(value));
    }

    public Condition nEq(long value)
    {
        return isNotEqualTo(value);
    }

    public Condition isNotEqualTo(double value)
    {
        return new GenericCondition(IS_NOT_EQUAL_TO, this, Double.valueOf(value));
    }

    public Condition nEq(double value)
    {
        return isNotEqualTo(value);
    }

    public Condition isGreaterThan(Number value)
    {
        return new GenericCondition(IS_GREATER_THAN, this, value);
    }

    public Condition gt(Number value)
    {
        return isGreaterThan(value);
    }

    public Condition isGreaterThan(long value)
    {
        return new GenericCondition(IS_GREATER_THAN, this, Long.valueOf(value));
    }

    public Condition gt(long value)
    {
        return isGreaterThan(value);
    }

    public Condition isGreaterThan(double value)
    {
        return new GenericCondition(IS_GREATER_THAN, this, Double.valueOf(value));
    }

    public Condition gt(double value)
    {
        return isGreaterThan(value);
    }

    public Condition isGreaterThan(NumberColumn<?, ?> otherColumn)
    {
        return new GenericCondition(IS_GREATER_THAN, this, otherColumn);
    }

    public Condition gt(NumberColumn<?, ?> otherColumn)
    {
        return isGreaterThan(otherColumn);
    }

    public Condition isGreaterThanOrEqualTo(Number value)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, value);
    }

    public Condition gtEq(Number value)
    {
        return isGreaterThanOrEqualTo(value);
    }

    public Condition isGreaterThanOrEqualTo(long value)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, Long.valueOf(value));
    }

    public Condition gtEq(long value)
    {
        return isGreaterThanOrEqualTo(value);
    }

    public Condition isGreaterThanOrEqualTo(double value)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, Double.valueOf(value));
    }

    public Condition gtEq(double value)
    {
        return isGreaterThanOrEqualTo(value);
    }

    public Condition isGreaterThanOrEqualTo(NumberColumn<?, ?> otherColumn)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, otherColumn);
    }

    public Condition gtEq(NumberColumn<?, ?> otherColumn)
    {
        return isGreaterThanOrEqualTo(otherColumn);
    }

    public Condition isLessThan(Number value)
    {
        return new GenericCondition(IS_LESS_THAN, this, value);
    }

    public Condition lt(Number value)
    {
        return isLessThan(value);
    }

    public Condition isLessThan(long value)
    {
        return new GenericCondition(IS_LESS_THAN, this, Long.valueOf(value));
    }

    public Condition lt(long value)
    {
        return isLessThan(value);
    }

    public Condition isLessThan(double value)
    {
        return new GenericCondition(IS_LESS_THAN, this, Double.valueOf(value));
    }

    public Condition lt(double value)
    {
        return isLessThan(value);
    }

    public Condition isLessThan(NumberColumn<?, ?> otherColumn)
    {
        return new GenericCondition(IS_LESS_THAN, this, otherColumn);
    }

    public Condition lt(NumberColumn<?, ?> otherColumn)
    {
        return isLessThan(otherColumn);
    }

    public Condition isLessThanOrEqualTo(Number value)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, value);
    }

    public Condition ltEq(Number value)
    {
        return isLessThanOrEqualTo(value);
    }

    public Condition isLessThanOrEqualTo(long value)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, Long.valueOf(value));
    }

    public Condition ltEq(long value)
    {
        return isLessThanOrEqualTo(value);
    }

    public Condition isLessThanOrEqualTo(double value)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, Double.valueOf(value));
    }

    public Condition ltEq(double value)
    {
        return isLessThanOrEqualTo(value);
    }

    public Condition isLessThanOrEqualTo(NumberColumn<?, ?> otherColumn)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, otherColumn);
    }

    public Condition ltEq(NumberColumn<?, ?> otherColumn)
    {
        return isLessThanOrEqualTo(otherColumn);
    }

    public Condition isBetween(Number value1, Number value2)
    {
        return new GenericCondition(IS_BETWEEN, this, value1, value2);
    }

    public Condition isBetween(long value1, long value2)
    {
        return new GenericCondition(IS_BETWEEN, this, Long.valueOf(value1), Long.valueOf(value2));
    }

    public Condition isBetween(double value1, double value2)
    {
        return new GenericCondition(IS_BETWEEN, this, Double.valueOf(value1), Double.valueOf(value2));
    }

    public Condition isBetween(Column otherColumn1, Column otherColumn2)
    {
        return new GenericCondition(IS_BETWEEN, this, otherColumn1, otherColumn2);
    }

    public Condition isBetween(Column otherColumn, Number value)
    {
        return new GenericCondition(IS_BETWEEN, this, otherColumn, value);
    }

    public Condition isBetween(Column otherColumn, long value)
    {
        return new GenericCondition(IS_BETWEEN, this, otherColumn, Long.valueOf(value));
    }

    public Condition isBetween(Column otherColumn, double value)
    {
        return new GenericCondition(IS_BETWEEN, this, otherColumn, Double.valueOf(value));
    }

    public Condition isBetween(Number value, Column otherColumn)
    {
        return new GenericCondition(IS_BETWEEN, this, value, otherColumn);
    }

    public Condition isBetween(long value, Column otherColumn)
    {
        return new GenericCondition(IS_BETWEEN, this, Long.valueOf(value), otherColumn);
    }

    public Condition isBetween(double value, Column otherColumn)
    {
        return new GenericCondition(IS_BETWEEN, this, Double.valueOf(value), otherColumn);
    }
}
