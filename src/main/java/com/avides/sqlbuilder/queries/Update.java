package com.avides.sqlbuilder.queries;

import static com.avides.sqlbuilder.conditions.CombinedCondition.getCopy;
import static com.avides.sqlbuilder.domain.ConditionType.WHERE;
import static com.avides.sqlbuilder.domain.ConditionType.WHERE_NOT;

import java.util.LinkedHashMap;
import java.util.Map;

import com.avides.sqlbuilder.columns.Column;
import com.avides.sqlbuilder.columns.number.NumberColumn;
import com.avides.sqlbuilder.columns.string.StringColumn;
import com.avides.sqlbuilder.conditions.Condition;
import com.avides.sqlbuilder.dialect.Dialect;
import com.avides.sqlbuilder.domain.ConditionType;
import com.avides.sqlbuilder.domain.PlainValuable;
import com.avides.sqlbuilder.domain.Valuable;
import com.avides.sqlbuilder.domain.ValuableColumn;
import com.avides.sqlbuilder.domain.ValuableFunction;
import com.avides.sqlbuilder.functions.Function;
import com.avides.sqlbuilder.schema.Table;
import com.avides.sqlbuilder.utils.Indentation;

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

    public void clearWheres(Update update)
    {
        update.where = null;
    }
}
