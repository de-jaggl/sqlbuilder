package de.jaggl.sqlbuilder.core.columns.datetime;

import static de.jaggl.sqlbuilder.core.conditions.GenericCondition.GenericConditionType.IS_BETWEEN;
import static de.jaggl.sqlbuilder.core.conditions.GenericCondition.GenericConditionType.IS_EQUAL_TO;
import static de.jaggl.sqlbuilder.core.conditions.GenericCondition.GenericConditionType.IS_GREATER_THAN;
import static de.jaggl.sqlbuilder.core.conditions.GenericCondition.GenericConditionType.IS_GREATER_THAN_OR_EQUAL_TO;
import static de.jaggl.sqlbuilder.core.conditions.GenericCondition.GenericConditionType.IS_LESS_THAN;
import static de.jaggl.sqlbuilder.core.conditions.GenericCondition.GenericConditionType.IS_LESS_THAN_OR_EQUAL_TO;
import static de.jaggl.sqlbuilder.core.conditions.GenericCondition.GenericConditionType.IS_NOT_EQUAL_TO;
import static de.jaggl.sqlbuilder.core.conditions.GenericCondition.GenericConditionType.IS_NOT_NULL;
import static de.jaggl.sqlbuilder.core.conditions.GenericCondition.GenericConditionType.IS_NULL;
import static java.sql.Types.DATE;

import java.time.LocalTime;

import de.jaggl.sqlbuilder.core.columns.Column;
import de.jaggl.sqlbuilder.core.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.core.conditions.Condition;
import de.jaggl.sqlbuilder.core.conditions.GenericCondition;
import de.jaggl.sqlbuilder.core.domain.Placeholder;
import de.jaggl.sqlbuilder.core.schema.Table;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@ToString(callSuper = true)
public class TimeColumn extends Column
{
    public TimeColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition, DATE);
    }

    public TimeColumn as(String alias)
    {
        return new TimeColumn(table, name, alias, columnDefinition);
    }

    public Condition isEqualTo(LocalTime value)
    {
        return value == null ? new GenericCondition(IS_NULL, this) : new GenericCondition(IS_EQUAL_TO, this, value);
    }

    public Condition eq(LocalTime value)
    {
        return isEqualTo(value);
    }

    public Condition isNotEqualTo(LocalTime value)
    {
        return value == null ? new GenericCondition(IS_NOT_NULL, this) : new GenericCondition(IS_NOT_EQUAL_TO, this, value);
    }

    public Condition nEq(LocalTime value)
    {
        return isNotEqualTo(value);
    }

    public Condition isAfter(LocalTime value)
    {
        return new GenericCondition(IS_GREATER_THAN, this, value);
    }

    public Condition isAfter(Column otherColumn)
    {
        return new GenericCondition(IS_GREATER_THAN, this, otherColumn);
    }

    public Condition isAfter(Placeholder placeholder)
    {
        return new GenericCondition(IS_GREATER_THAN, this, placeholder);
    }

    public Condition isAfterOrEqualTo(LocalTime value)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, value);
    }

    public Condition isAfterOrEqualTo(Column otherColumn)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, otherColumn);
    }

    public Condition isAfterOrEqualTo(Placeholder placeholder)
    {
        return new GenericCondition(IS_GREATER_THAN_OR_EQUAL_TO, this, placeholder);
    }

    public Condition isBefore(LocalTime value)
    {
        return new GenericCondition(IS_LESS_THAN, this, value);
    }

    public Condition isBefore(Column otherColumn)
    {
        return new GenericCondition(IS_LESS_THAN, this, otherColumn);
    }

    public Condition isBefore(Placeholder placeholder)
    {
        return new GenericCondition(IS_LESS_THAN, this, placeholder);
    }

    public Condition isBeforeOrEqualTo(LocalTime value)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, value);
    }

    public Condition isBeforeOrEqualTo(Column otherColumn)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, otherColumn);
    }

    public Condition isBeforeOrEqualTo(Placeholder placeholder)
    {
        return new GenericCondition(IS_LESS_THAN_OR_EQUAL_TO, this, placeholder);
    }

    public Condition isBetween(LocalTime value1, LocalTime value2)
    {
        return new GenericCondition(IS_BETWEEN, this, value1, value2);
    }

    public Condition isBetween(Column otherColumn1, Column otherColumn2)
    {
        return new GenericCondition(IS_BETWEEN, this, otherColumn1, otherColumn2);
    }

    public Condition isBetween(LocalTime value, Column otherColumn)
    {
        return new GenericCondition(IS_BETWEEN, this, value, otherColumn);
    }

    public Condition isBetween(Column otherColumn, LocalTime value)
    {
        return new GenericCondition(IS_BETWEEN, this, otherColumn, value);
    }

    public Condition isBetween(Column otherColumn, Placeholder placeholder)
    {
        return new GenericCondition(IS_BETWEEN, this, otherColumn, placeholder);
    }

    public Condition isBetween(LocalTime value, Placeholder placeholder)
    {
        return new GenericCondition(IS_BETWEEN, this, value, placeholder);
    }

    public Condition isBetween(Placeholder placeholder, LocalTime value)
    {
        return new GenericCondition(IS_BETWEEN, this, placeholder, value);
    }

    public Condition isBetween(Placeholder placeholder, Column otherColumn)
    {
        return new GenericCondition(IS_BETWEEN, this, placeholder, otherColumn);
    }
}
