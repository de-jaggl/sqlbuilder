package com.avides.sqlbuilder.queries;

import static com.avides.sqlbuilder.conditions.CombinedCondition.getCopy;
import static com.avides.sqlbuilder.domain.ConditionType.WHERE;
import static com.avides.sqlbuilder.domain.ConditionType.WHERE_NOT;

import com.avides.sqlbuilder.conditions.Condition;
import com.avides.sqlbuilder.dialect.Dialect;
import com.avides.sqlbuilder.domain.ConditionType;
import com.avides.sqlbuilder.domain.Limit;
import com.avides.sqlbuilder.schema.Table;
import com.avides.sqlbuilder.utils.Indentation;

public class Delete
{
    private Table table;
    private Condition where;
    private ConditionType whereConditionType;
    private Limit limitation;

    Delete()
    {
    }

    Delete(Delete delete)
    {
        table = delete.table;
        where = getCopy(delete.where);
        whereConditionType = delete.whereConditionType;
        limitation = delete.limitation;
    }

    public Table getTable()
    {
        return table;
    }

    public Condition getWhere()
    {
        return where;
    }

    public ConditionType getWhereConditionType()
    {
        return whereConditionType;
    }

    public Limit getLimitation()
    {
        return limitation;
    }

    public Delete from(Table deleteTable)
    {
        table = deleteTable;
        return this;
    }

    public Delete where(Condition condition)
    {
        where = condition;
        whereConditionType = WHERE;
        return this;
    }

    public Delete whereNot(Condition condition)
    {
        where = condition;
        whereConditionType = WHERE_NOT;
        return this;
    }

    public Delete limit(long limit, long offset)
    {
        limitation = new Limit(limit, offset);
        return this;
    }

    public Delete limit(long limit)
    {
        limitation = new Limit(limit, 0);
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

    public void clearWheres(Delete delete)
    {
        delete.where = null;
    }

    public void clearLimit(Delete delete)
    {
        delete.limitation = null;
    }
}
