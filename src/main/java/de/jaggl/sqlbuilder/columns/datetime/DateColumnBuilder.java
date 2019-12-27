package de.jaggl.sqlbuilder.columns.datetime;

import java.time.LocalDate;

import de.jaggl.sqlbuilder.columns.ColumnBuilder;
import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.columns.configurable.DefaultValueColumnBuilder;
import de.jaggl.sqlbuilder.columns.configurable.NullableColumnBuilder;
import de.jaggl.sqlbuilder.schema.Table;

public class DateColumnBuilder extends ColumnBuilder<DateColumn>
        implements NullableColumnBuilder<DateColumnBuilder>, DefaultValueColumnBuilder<DateColumnBuilder, LocalDate>
{
    protected boolean isNullable = true;
    private boolean isDefaultNull = true;
    protected LocalDate defaultValue;

    public DateColumnBuilder(Table table, String name)
    {
        super(table, name);
    }

    @Override
    public DateColumnBuilder nullable(boolean nullable)
    {
        isNullable = nullable;
        return this;
    }

    @Override
    public DateColumnBuilder defaultValue(LocalDate value)
    {
        isDefaultNull = false;
        defaultValue = value;
        return this;
    }

    @Override
    public DateColumnBuilder defaultNull()
    {
        isNullable = true;
        defaultValue = null;
        isDefaultNull = true;
        return this;
    }

    @Override
    public DateColumnBuilder noDefault()
    {
        defaultValue = null;
        isDefaultNull = false;
        return this;
    }

    @Override
    protected DateColumn getColumnInstance()
    {
        return new DateColumn(table, name, null, new ColumnDefinition("DATE", null, isNullable, isDefaultNull, false, false, defaultValue));
    }
}
