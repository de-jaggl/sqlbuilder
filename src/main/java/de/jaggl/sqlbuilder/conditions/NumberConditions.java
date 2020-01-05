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

import de.jaggl.sqlbuilder.columns.Column;

public interface NumberConditions
{
    default Condition isEqualTo(Number value)
    {
        return value == null ? new GenericCondition(IS_NULL, this) : new GenericCondition(IS_EQUAL_TO, this, value);
    }

    default Condition eq(Number value)
    {
        return isEqualTo(value);
    }

    default Condition isEqualTo(long value)
    {
        return new GenericCondition(IS_EQUAL_TO, this, Long.valueOf(value));
    }

    default Condition eq(long value)
    {
        return isEqualTo(value);
    }

    default Condition isEqualTo(double value)
    {
        return new GenericCondition(IS_EQUAL_TO, this, Double.valueOf(value));
    }

    default Condition eq(double value)
    {
        return isEqualTo(value);
    }

    default Condition isNotEqualTo(Number value)
    {
        return value == null ? new GenericCondition(IS_NOT_NULL, this) : new GenericCondition(IS_NOT_EQUAL_TO, this, value);
    }

    default Condition nEq(Number value)
    {
        return isNotEqualTo(value);
    }

    default Condition isNotEqualTo(long value)
    {
        return new GenericCondition(IS_NOT_EQUAL_TO, this, Long.valueOf(value));
    }

    default Condition nEq(long value)
    {
        return isNotEqualTo(value);
    }

    default Condition isNotEqualTo(double value)
    {
        return new GenericCondition(IS_NOT_EQUAL_TO, this, Double.valueOf(value));
    }

    default Condition nEq(double value)
    {
        return isNotEqualTo(value);
    }

    default Condition isLessThan(Number value)
    {
        return new GenericCondition(IS_LESS_THAN, this, value);
    }

    default Condition lt(Number value)
    {
        return isLessThan(value);
    }

    default Condition isLessThan(long value)
    {
        return new GenericCondition(IS_LESS_THAN, this, Long.valueOf(value));
    }

    default Condition lt(long value)
    {
        return isLessThan(value);
    }

    default Condition isLessThan(double value)
    {
        return new GenericCondition(IS_LESS_THAN, this, Double.valueOf(value));
    }

    default Condition lt(double value)
    {
        return isLessThan(value);
    }

    default Condition isLessThan(Column otherColumn)
    {
        return new GenericCondition(IS_LESS_THAN, this, otherColumn);
    }

    default Condition lt(Column otherColumn)
    {
        return isLessThan(otherColumn);
    }

    default Condition isGreaterThan(Number value)
    {
        return new GenericCondition(IS_GREATER_THAN, this, value);
    }

    default Condition gt(Number value)
    {
        return isGreaterThan(value);
    }

    default Condition isGreaterThan(long value)
    {
        return new GenericCondition(IS_GREATER_THAN, this, Long.valueOf(value));
    }

    default Condition gt(long value)
    {
        return isGreaterThan(value);
    }

    default Condition isGreaterThan(double value)
    {
        return new GenericCondition(IS_GREATER_THAN, this, Double.valueOf(value));
    }

    default Condition gt(double value)
    {
        return isGreaterThan(value);
    }

    default Condition isGreaterThan(Column otherColumn)
    {
        return new GenericCondition(IS_GREATER_THAN, this, otherColumn);
    }

    default Condition gt(Column otherColumn)
    {
        return isGreaterThan(otherColumn);
    }

    default Condition isLessThanOrEqualTo(Number value)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, value);
    }

    default Condition ltEq(Number value)
    {
        return isLessThanOrEqualTo(value);
    }

    default Condition isLessThanOrEqualTo(long value)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, Long.valueOf(value));
    }

    default Condition ltEq(long value)
    {
        return isLessThanOrEqualTo(value);
    }

    default Condition isLessThanOrEqualTo(double value)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, Double.valueOf(value));
    }

    default Condition ltEq(double value)
    {
        return isLessThanOrEqualTo(value);
    }

    default Condition isLessThanOrEqualTo(Column otherColumn)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, otherColumn);
    }

    default Condition ltEq(Column otherColumn)
    {
        return isLessThanOrEqualTo(otherColumn);
    }

    default Condition isGreaterThanOrEqualTo(Number value)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, value);
    }

    default Condition gtEq(Number value)
    {
        return isGreaterThanOrEqualTo(value);
    }

    default Condition isGreaterThanOrEqualTo(long value)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, Long.valueOf(value));
    }

    default Condition gtEq(long value)
    {
        return isGreaterThanOrEqualTo(value);
    }

    default Condition isGreaterThanOrEqualTo(double value)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, Double.valueOf(value));
    }

    default Condition gtEq(double value)
    {
        return isGreaterThanOrEqualTo(value);
    }

    default Condition isGreaterThanOrEqualTo(Column otherColumn)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, otherColumn);
    }

    default Condition gtEq(Column otherColumn)
    {
        return isGreaterThanOrEqualTo(otherColumn);
    }

    default Condition isBetween(Number value1, Number value2)
    {
        return new GenericCondition(IS_BETWEEN, this, value1, value2);
    }

    default Condition isBetween(long value1, long value2)
    {
        return new GenericCondition(IS_BETWEEN, this, Long.valueOf(value1), Long.valueOf(value2));
    }

    default Condition isBetween(double value1, double value2)
    {
        return new GenericCondition(IS_BETWEEN, this, Double.valueOf(value1), Double.valueOf(value2));
    }

    default Condition isBetween(Column otherColumn1, Column otherColumn2)
    {
        return new GenericCondition(IS_BETWEEN, this, otherColumn1, otherColumn2);
    }

    default Condition isBetween(Column otherColumn, Number value)
    {
        return new GenericCondition(IS_BETWEEN, this, otherColumn, value);
    }

    default Condition isBetween(Column otherColumn, long value)
    {
        return new GenericCondition(IS_BETWEEN, this, otherColumn, Long.valueOf(value));
    }

    default Condition isBetween(Column otherColumn, double value)
    {
        return new GenericCondition(IS_BETWEEN, this, otherColumn, Double.valueOf(value));
    }

    default Condition isBetween(Number value, Column otherColumn)
    {
        return new GenericCondition(IS_BETWEEN, this, value, otherColumn);
    }

    default Condition isBetween(long value, Column otherColumn)
    {
        return new GenericCondition(IS_BETWEEN, this, Long.valueOf(value), otherColumn);
    }

    default Condition isBetween(double value, Column otherColumn)
    {
        return new GenericCondition(IS_BETWEEN, this, Double.valueOf(value), otherColumn);
    }
}
