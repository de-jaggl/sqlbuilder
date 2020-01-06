package de.jaggl.sqlbuilder.queries;

import static de.jaggl.sqlbuilder.conditions.CombinedCondition.getCopy;
import static de.jaggl.sqlbuilder.domain.ConditionType.WHERE;
import static de.jaggl.sqlbuilder.domain.ConditionType.WHERE_NOT;
import static lombok.AccessLevel.PACKAGE;

import java.util.LinkedHashMap;
import java.util.Map;

import de.jaggl.sqlbuilder.columns.Column;
import de.jaggl.sqlbuilder.columns.number.NumberColumn;
import de.jaggl.sqlbuilder.columns.string.StringColumn;
import de.jaggl.sqlbuilder.conditions.Condition;
import de.jaggl.sqlbuilder.dialect.Dialect;
import de.jaggl.sqlbuilder.domain.ConditionType;
import de.jaggl.sqlbuilder.domain.PlainValuable;
import de.jaggl.sqlbuilder.domain.Valuable;
import de.jaggl.sqlbuilder.domain.ValuableColumn;
import de.jaggl.sqlbuilder.domain.ValuableFunction;
import de.jaggl.sqlbuilder.functions.Function;
import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.utils.Indentation;
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
