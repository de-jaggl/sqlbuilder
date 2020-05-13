package de.jaggl.sqlbuilder.core.columns.datetime;

import java.time.LocalDate;

import de.jaggl.sqlbuilder.core.columns.ColumnBuilder;
import de.jaggl.sqlbuilder.core.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.core.schema.Table;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class TimeColumnBuilder extends ColumnBuilder<TimeColumn, TimeColumnBuilder, LocalDate>
{
    public TimeColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    @Override
    protected TimeColumn getColumnInstance()
    {
        return new TimeColumn(table, name, null, new ColumnDefinition("TIME", null, isNullable, isDefaultNull, false, false, defaultValue));
    }
}
