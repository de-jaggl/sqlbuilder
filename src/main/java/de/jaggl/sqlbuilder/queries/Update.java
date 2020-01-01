package de.jaggl.sqlbuilder.queries;

import static de.jaggl.sqlbuilder.conditions.CombinedCondition.getCopy;
import static de.jaggl.sqlbuilder.domain.ConditionType.WHERE;
import static de.jaggl.sqlbuilder.domain.ConditionType.WHERE_NOT;

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

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class Update
{
    private Table table;
    private Map<Column, Valuable> values = new LinkedHashMap<>();
    private Condition where;
    private ConditionType whereConditionType;

    Update(Table table)
    {
        this.table = table;
    }

    Update(Update update)
    {
        table = update.table;
        values = update.values != null ? new LinkedHashMap<>(update.values) : null;
        where = getCopy(update.where);
        whereConditionType = update.whereConditionType;
    }

    public Table getTable()
    {
        return table;
    }

    public Map<Column, Valuable> getValues()
    {
        return values;
    }

    public Condition getWhere()
    {
        return where;
    }

    public ConditionType getWhereConditionType()
    {
        return whereConditionType;
    }

    public Update set(StringColumn<?> column, CharSequence value)
    {
        return addValue(column, new PlainValuable(value));
    }

    public Update set(NumberColumn<?, ?> column, Number value)
    {
        return addValue(column, new PlainValuable(value));
    }

    public Update set(NumberColumn<?, ?> column, long value)
    {
        return addValue(column, new PlainValuable(Long.valueOf(value)));
    }

    public Update set(NumberColumn<?, ?> column, double value)
    {
        return addValue(column, new PlainValuable(Double.valueOf(value)));
    }

    public Update set(Column column, Function function)
    {
        return addValue(column, new ValuableFunction(function));
    }

    public Update set(Column column, Column otherColumn)
    {
        return addValue(column, new ValuableColumn(otherColumn));
    }

    private Update addValue(Column column, Valuable value)
    {
        values.put(column, value);
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

    public static void clearWheres(Update update)
    {
        update.where = null;
    }

    public static Update copy(Update update)
    {
        return new Update(update);
    }
}
