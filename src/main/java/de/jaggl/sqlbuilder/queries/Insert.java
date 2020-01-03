package de.jaggl.sqlbuilder.queries;

import static lombok.AccessLevel.PACKAGE;

import java.util.LinkedHashMap;
import java.util.Map;

import de.jaggl.sqlbuilder.columns.Column;
import de.jaggl.sqlbuilder.columns.number.NumberColumn;
import de.jaggl.sqlbuilder.columns.string.StringColumn;
import de.jaggl.sqlbuilder.dialect.Dialect;
import de.jaggl.sqlbuilder.domain.PlainValuable;
import de.jaggl.sqlbuilder.domain.Valuable;
import de.jaggl.sqlbuilder.domain.ValuableColumn;
import de.jaggl.sqlbuilder.domain.ValuableFunction;
import de.jaggl.sqlbuilder.functions.Function;
import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.utils.Indentation;
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
public class Insert implements Query
{
    private Table table;
    private Map<Column, Valuable> values = new LinkedHashMap<>();

    Insert(Insert insert)
    {
        table = insert.table;
        values = insert.values != null ? new LinkedHashMap<>(insert.values) : null;
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

    @Override
    public String build(Dialect dialect, Indentation indentation)
    {
        return dialect.build(this, indentation);
    }

    public static Insert copy(Insert insert)
    {
        return new Insert(insert);
    }
}
