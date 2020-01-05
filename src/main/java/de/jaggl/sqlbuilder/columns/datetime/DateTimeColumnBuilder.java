package de.jaggl.sqlbuilder.columns.datetime;

import java.time.LocalDateTime;

import de.jaggl.sqlbuilder.columns.ColumnBuilder;
import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class DateTimeColumnBuilder extends ColumnBuilder<DateTimeColumn, DateTimeColumnBuilder, LocalDateTime>
{
    public DateTimeColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    @Override
    protected DateTimeColumn getColumnInstance()
    {
        return new DateTimeColumn(table, name, null, new ColumnDefinition("DATETIME", null, isNullable, isDefaultNull, false, false, defaultValue));
    }
}
