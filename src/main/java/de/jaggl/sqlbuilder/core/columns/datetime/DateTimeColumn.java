package de.jaggl.sqlbuilder.core.columns.datetime;

import static java.sql.Types.TIMESTAMP;

import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.util.function.Function;

import de.jaggl.sqlbuilder.core.columns.Column;
import de.jaggl.sqlbuilder.core.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.core.conditions.DateTimeConditions;
import de.jaggl.sqlbuilder.core.schema.Table;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@ToString(callSuper = true)
public class DateTimeColumn extends Column implements DateTimeConditions
{
    @Override
    public Function<ZonedDateTime, Temporal> getDateConversion()
    {
        return ZonedDateTime::toLocalDateTime;
    }

    public DateTimeColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition, TIMESTAMP);
    }

    public DateTimeColumn as(String alias)
    {
        return new DateTimeColumn(table, name, alias, columnDefinition);
    }
}
