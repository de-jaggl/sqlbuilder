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

    public Condition isEqualTo(Number number)
    {
        return number == null ? new GenericCondition(IS_NULL, this) : new GenericCondition(IS_EQUAL_TO, this, number);
    }

    public Condition isEqualTo(long number)
    {
        return new GenericCondition(IS_EQUAL_TO, this, Long.valueOf(number));
    }

    public Condition isEqualTo(double number)
    {
        return new GenericCondition(IS_EQUAL_TO, this, Double.valueOf(number));
    }

    public Condition isNotEqualTo(Number number)
    {
        return number == null ? new GenericCondition(IS_NOT_NULL, this) : new GenericCondition(IS_NOT_EQUAL_TO, this, number);
    }

    public Condition isNotEqualTo(long number)
    {
        return new GenericCondition(IS_NOT_EQUAL_TO, this, Long.valueOf(number));
    }

    public Condition isNotEqualTo(double number)
    {
        return new GenericCondition(IS_NOT_EQUAL_TO, this, Double.valueOf(number));
    }

    public Condition isGreaterThan(Number number)
    {
        return new GenericCondition(IS_GREATER_THAN, this, number);
    }

    public Condition isGreaterThan(long number)
    {
        return new GenericCondition(IS_GREATER_THAN, this, Long.valueOf(number));
    }

    public Condition isGreaterThan(double number)
    {
        return new GenericCondition(IS_GREATER_THAN, this, Double.valueOf(number));
    }

    public Condition isGreaterThan(NumberColumn<?, ?> otherColumn)
    {
        return new GenericCondition(IS_GREATER_THAN, this, otherColumn);
    }

    public Condition isGreaterThanOrEqualTo(Number number)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, number);
    }

    public Condition isGreaterThanOrEqualTo(long number)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, Long.valueOf(number));
    }

    public Condition isGreaterThanOrEqualTo(double number)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, Double.valueOf(number));
    }

    public Condition isGreaterThanOrEqualTo(NumberColumn<?, ?> otherColumn)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, otherColumn);
    }

    public Condition isLessThan(Number number)
    {
        return new GenericCondition(IS_LESS_THAN, this, number);
    }

    public Condition isLessThan(long number)
    {
        return new GenericCondition(IS_LESS_THAN, this, Long.valueOf(number));
    }

    public Condition isLessThan(double number)
    {
        return new GenericCondition(IS_LESS_THAN, this, Double.valueOf(number));
    }

    public Condition isLessThan(NumberColumn<?, ?> otherColumn)
    {
        return new GenericCondition(IS_LESS_THAN, this, otherColumn);
    }

    public Condition isLessThanOrEqualTo(Number number)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, number);
    }

    public Condition isLessThanOrEqualTo(long number)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, Long.valueOf(number));
    }

    public Condition isLessThanOrEqualTo(double number)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, Double.valueOf(number));
    }

    public Condition isLessThanOrEqualTo(NumberColumn<?, ?> otherColumn)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, otherColumn);
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
