package de.jaggl.sqlbuilder.columns.number;

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
import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.conditions.Condition;
import de.jaggl.sqlbuilder.conditions.GenericCondition;
import de.jaggl.sqlbuilder.schema.Table;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public abstract class NumberColumn<T extends NumberColumn<T, N>, N extends Number> extends Column
{
    public NumberColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition);
    }

    public Condition isEqualTo(Number value)
    {
        return value == null ? new GenericCondition(IS_NULL, this) : new GenericCondition(IS_EQUAL_TO, this, value);
    }

    public Condition isEqualTo(long value)
    {
        return new GenericCondition(IS_EQUAL_TO, this, Long.valueOf(value));
    }

    public Condition isEqualTo(double value)
    {
        return new GenericCondition(IS_EQUAL_TO, this, Double.valueOf(value));
    }

    public Condition isNotEqualTo(Number value)
    {
        return value == null ? new GenericCondition(IS_NOT_NULL, this) : new GenericCondition(IS_NOT_EQUAL_TO, this, value);
    }

    public Condition isNotEqualTo(long value)
    {
        return new GenericCondition(IS_NOT_EQUAL_TO, this, Long.valueOf(value));
    }

    public Condition isNotEqualTo(double value)
    {
        return new GenericCondition(IS_NOT_EQUAL_TO, this, Double.valueOf(value));
    }

    public Condition isLessThan(Number value)
    {
        return new GenericCondition(IS_LESS_THAN, this, value);
    }

    public Condition isLessThan(long value)
    {
        return new GenericCondition(IS_LESS_THAN, this, Long.valueOf(value));
    }

    public Condition isLessThan(double value)
    {
        return new GenericCondition(IS_LESS_THAN, this, Double.valueOf(value));
    }

    public Condition isLessThan(Column otherColumn)
    {
        return new GenericCondition(IS_LESS_THAN, this, otherColumn);
    }

    public Condition isGreaterThan(Number value)
    {
        return new GenericCondition(IS_GREATER_THAN, this, value);
    }

    public Condition isGreaterThan(long value)
    {
        return new GenericCondition(IS_GREATER_THAN, this, Long.valueOf(value));
    }

    public Condition isGreaterThan(double value)
    {
        return new GenericCondition(IS_GREATER_THAN, this, Double.valueOf(value));
    }

    public Condition isGreaterThan(Column otherColumn)
    {
        return new GenericCondition(IS_GREATER_THAN, this, otherColumn);
    }

    public Condition isLessThanOrEqualTo(Number value)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, value);
    }

    public Condition isLessThanOrEqualTo(long value)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, Long.valueOf(value));
    }

    public Condition isLessThanOrEqualTo(double value)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, Double.valueOf(value));
    }

    public Condition isLessThanOrEqualTo(Column otherColumn)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, otherColumn);
    }

    public Condition isGreaterThanOrEqualTo(Number value)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, value);
    }

    public Condition isGreaterThanOrEqualTo(long value)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, Long.valueOf(value));
    }

    public Condition isGreaterThanOrEqualTo(double value)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, Double.valueOf(value));
    }

    public Condition isGreaterThanOrEqualTo(Column otherColumn)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, otherColumn);
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
