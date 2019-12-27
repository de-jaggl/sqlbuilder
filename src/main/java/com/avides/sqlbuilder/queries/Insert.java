package com.avides.sqlbuilder.queries;

import java.util.LinkedHashMap;
import java.util.Map;

import com.avides.sqlbuilder.columns.Column;
import com.avides.sqlbuilder.columns.number.NumberColumn;
import com.avides.sqlbuilder.columns.string.StringColumn;
import com.avides.sqlbuilder.dialect.Dialect;
import com.avides.sqlbuilder.domain.PlainValuable;
import com.avides.sqlbuilder.domain.Valuable;
import com.avides.sqlbuilder.domain.ValuableColumn;
import com.avides.sqlbuilder.domain.ValuableFunction;
import com.avides.sqlbuilder.functions.Function;
import com.avides.sqlbuilder.schema.Table;
import com.avides.sqlbuilder.utils.Indentation;

public class Insert
{
    private Table table;
    private Map<Column, Valuable> values = new LinkedHashMap<>();

    Insert()
    {
    }

    Insert(Insert insert)
    {
        table = insert.table;
        values = insert.values != null ? new LinkedHashMap<>(insert.values) : null;
    }

    public Table getTable()
    {
        return table;
    }

    public Map<Column, Valuable> getValues()
    {
        return values;
    }

    public Insert into(Table insertTable)
    {
        table = insertTable;
        return this;
    }

    public Insert set(StringColumn<?> column, CharSequence value)
    {
        return addValue(column, new PlainValuable(value));
    }

    public Insert set(NumberColumn<?, ?> column, Number value)
    {
        return addValue(column, new PlainValuable(value));
    }

    public Insert set(NumberColumn<?, ?> column, long value)
    {
        return addValue(column, new PlainValuable(Long.valueOf(value)));
    }

    public Insert set(NumberColumn<?, ?> column, double value)
    {
        return addValue(column, new PlainValuable(Double.valueOf(value)));
    }

    public Insert set(Column column, Function function)
    {
        return addValue(column, new ValuableFunction(function));
    }

    public Insert set(Column column, Column otherColumn)
    {
        return addValue(column, new ValuableColumn(otherColumn));
    }

    private Insert addValue(Column column, Valuable value)
    {
        values.put(column, value);
        return this;
    }

    public String build(Dialect dialect)
    {
        return dialect.build(this, Indentation.disabled());
    }

    public String build(Dialect dialect, Indentation indentation)
    {
        return dialect.build(this, indentation);
    }

    public String build(String dialectName)
    {
        return Dialect.forName(dialectName).build(this, Indentation.disabled());
    }

    public String build(String dialectName, Indentation indentation)
    {
        return Dialect.forName(dialectName).build(this, indentation);
    }
}
