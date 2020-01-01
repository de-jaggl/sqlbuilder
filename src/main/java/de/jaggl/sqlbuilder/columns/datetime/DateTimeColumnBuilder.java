package de.jaggl.sqlbuilder.columns.datetime;

import java.time.LocalDateTime;

import de.jaggl.sqlbuilder.columns.ColumnBuilder;
import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.columns.configurable.DefaultValueColumnBuilder;
import de.jaggl.sqlbuilder.columns.configurable.NullableColumnBuilder;
import de.jaggl.sqlbuilder.schema.Table;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class DateTimeColumnBuilder extends ColumnBuilder<DateTimeColumn>
        implements NullableColumnBuilder<DateTimeColumnBuilder>, DefaultValueColumnBuilder<DateTimeColumnBuilder, LocalDateTime>
{
    protected boolean isNullable = true;
    private boolean isDefaultNull = true;
    protected LocalDateTime defaultValue;

    public DateTimeColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    @Override
    public DateTimeColumnBuilder nullable(boolean nullable)
    {
        isNullable = nullable;
        return this;
    }

    @Override
    public DateTimeColumnBuilder defaultValue(LocalDateTime value)
    {
        isDefaultNull = false;
        defaultValue = value;
        return this;
    }

    @Override
    public DateTimeColumnBuilder defaultNull()
    {
        isNullable = true;
        defaultValue = null;
        isDefaultNull = true;
        return this;
    }

    @Override
    public DateTimeColumnBuilder noDefault()
    {
        defaultValue = null;
        isDefaultNull = false;
        return this;
    }

    @Override
    protected DateTimeColumn getColumnInstance()
    {
        return new DateTimeColumn(table, name, null, new ColumnDefinition("DATETIME", null, isNullable, isDefaultNull, false, false, defaultValue));
    }
}
