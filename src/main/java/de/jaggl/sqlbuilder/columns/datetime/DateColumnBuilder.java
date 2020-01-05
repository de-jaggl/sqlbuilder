package de.jaggl.sqlbuilder.columns.datetime;

import java.time.LocalDate;

import de.jaggl.sqlbuilder.columns.ColumnBuilder;
import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class DateColumnBuilder extends ColumnBuilder<DateColumn, DateColumnBuilder, LocalDate>
{
    public DateColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    @Override
    protected DateColumn getColumnInstance()
    {
        return new DateColumn(table, name, null, new ColumnDefinition("DATE", null, isNullable, isDefaultNull, false, false, defaultValue));
    }
}
