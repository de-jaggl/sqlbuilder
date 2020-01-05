package de.jaggl.sqlbuilder.columns.datetime;

import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.util.function.Function;

import de.jaggl.sqlbuilder.columns.Column;
import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.conditions.DateTimeConditions;
import de.jaggl.sqlbuilder.schema.Table;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@ToString(callSuper = true)
public class DateColumn extends Column implements DateTimeConditions
{
    @Override
    public Function<ZonedDateTime, Temporal> getDateConversion()
    {
        return ZonedDateTime::toLocalDate;
    }

    public DateColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition);
    }

    public DateColumn as(String alias)
    {
        return new DateColumn(table, name, alias, columnDefinition);
    }
}
