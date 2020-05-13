package de.jaggl.sqlbuilder.core.queries;

import static lombok.AccessLevel.PACKAGE;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

import de.jaggl.sqlbuilder.core.columns.Column;
import de.jaggl.sqlbuilder.core.columns.datetime.DateColumn;
import de.jaggl.sqlbuilder.core.columns.datetime.DateTimeColumn;
import de.jaggl.sqlbuilder.core.columns.datetime.TimeColumn;
import de.jaggl.sqlbuilder.core.columns.number.NumberColumn;
import de.jaggl.sqlbuilder.core.columns.string.StringColumn;
import de.jaggl.sqlbuilder.core.dialect.Dialect;
import de.jaggl.sqlbuilder.core.domain.Placeholder;
import de.jaggl.sqlbuilder.core.domain.PlainValuable;
import de.jaggl.sqlbuilder.core.domain.Valuable;
import de.jaggl.sqlbuilder.core.domain.ValuableColumn;
import de.jaggl.sqlbuilder.core.domain.ValuableFunction;
import de.jaggl.sqlbuilder.core.functions.Function;
import de.jaggl.sqlbuilder.core.queryexecutor.QueryExecutor;
import de.jaggl.sqlbuilder.core.schema.Table;
import de.jaggl.sqlbuilder.core.utils.Indentation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@NoArgsConstructor(access = PACKAGE)
@Getter
@ToString
public class Insert implements UpdatebleQuery
{
    private Table table;
    private Map<Column, Valuable> values = new LinkedHashMap<>();

    Insert(Insert insert)
    {
        table = insert.table;
        values = new LinkedHashMap<>(insert.values);
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

    public Insert set(Column column, Placeholder placeholder)
    {
        return addValue(column, placeholder);
    }

    public Insert set(Column column, Function function)
    {
        return addValue(column, new ValuableFunction(function));
    }

    public Insert set(Column column, Column otherColumn)
    {
        return addValue(column, new ValuableColumn(otherColumn));
    }

    public Insert set(DateColumn column, LocalDate value)
    {
        return addValue(column, new PlainValuable(value));
    }

    public Insert set(DateTimeColumn column, LocalDateTime value)
    {
        return addValue(column, new PlainValuable(value));
    }

    public Insert set(TimeColumn column, LocalTime value)
    {
        return addValue(column, new PlainValuable(value));
    }

    private Insert addValue(Column column, Valuable value)
    {
        values.put(column, value);
        return this;
    }

    @Override
    public String build(Dialect dialect, Indentation indentation)
    {
        return dialect.build(this, indentation);
    }

    public static Insert copy(Insert insert)
    {
        return new Insert(insert);
    }

    public long executeAndReturnKey(QueryExecutor queryExecutor)
    {
        return queryExecutor.executeAndReturnKey(this);
    }
}
