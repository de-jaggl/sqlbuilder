package de.jaggl.sqlbuilder.core.queries;

import static de.jaggl.sqlbuilder.core.conditions.CombinedCondition.getCopy;
import static de.jaggl.sqlbuilder.core.domain.ConditionType.WHERE;
import static de.jaggl.sqlbuilder.core.domain.ConditionType.WHERE_NOT;
import static lombok.AccessLevel.PACKAGE;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import de.jaggl.sqlbuilder.core.columns.Column;
import de.jaggl.sqlbuilder.core.columns.datetime.DateColumn;
import de.jaggl.sqlbuilder.core.columns.datetime.DateTimeColumn;
import de.jaggl.sqlbuilder.core.columns.number.NumberColumn;
import de.jaggl.sqlbuilder.core.columns.string.StringColumn;
import de.jaggl.sqlbuilder.core.conditions.Condition;
import de.jaggl.sqlbuilder.core.dialect.Dialect;
import de.jaggl.sqlbuilder.core.domain.ConditionType;
import de.jaggl.sqlbuilder.core.domain.PlainValuable;
import de.jaggl.sqlbuilder.core.domain.Valuable;
import de.jaggl.sqlbuilder.core.domain.ValuableColumn;
import de.jaggl.sqlbuilder.core.domain.ValuableFunction;
import de.jaggl.sqlbuilder.core.functions.Function;
import de.jaggl.sqlbuilder.core.schema.Table;
import de.jaggl.sqlbuilder.core.utils.Indentation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@RequiredArgsConstructor(access = PACKAGE)
@Getter
@ToString
public class Update implements UpdatebleQuery
{
    private final Table table;
    private Map<Column, Valuable> values = new LinkedHashMap<>();
    private Condition where;
    private ConditionType whereConditionType;

    Update(Update update)
    {
        table = update.table;
        values = new LinkedHashMap<>(update.values);
        where = getCopy(update.where);
        whereConditionType = update.whereConditionType;
    }

    public Update set(StringColumn<?> column, CharSequence value)
    {
        return set(column, new PlainValuable(value));
    }

    public Update set(NumberColumn<?, ?> column, Number value)
    {
        return set(column, new PlainValuable(value));
    }

    public Update set(NumberColumn<?, ?> column, long value)
    {
        return set(column, new PlainValuable(Long.valueOf(value)));
    }

    public Update set(NumberColumn<?, ?> column, double value)
    {
        return set(column, new PlainValuable(Double.valueOf(value)));
    }

    public Update set(Column column, Function function)
    {
        return set(column, new ValuableFunction(function));
    }

    public Update set(Column column, Column otherColumn)
    {
        return set(column, new ValuableColumn(otherColumn));
    }

    public Update set(DateColumn column, LocalDate value)
    {
        return set(column, new PlainValuable(value));
    }

    public Update set(DateTimeColumn column, LocalDateTime value)
    {
        return set(column, new PlainValuable(value));
    }

    public Update set(Column column, Valuable valuable)
    {
        values.put(column, valuable);
        return this;
    }

    public Update where(Condition condition)
    {
        where = condition;
        whereConditionType = WHERE;
        return this;
    }

    public Update whereNot(Condition condition)
    {
        where = condition;
        whereConditionType = WHERE_NOT;
        return this;
    }

    @Override
    public String build(Dialect dialect, Indentation indentation)
    {
        return dialect.build(this, indentation);
    }

    public static void clearWheres(Update update)
    {
        update.where = null;
    }

    public static Update copy(Update update)
    {
        return new Update(update);
    }
}
