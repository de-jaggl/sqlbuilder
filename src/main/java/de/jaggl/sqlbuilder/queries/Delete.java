package de.jaggl.sqlbuilder.queries;

import static de.jaggl.sqlbuilder.conditions.CombinedCondition.getCopy;
import static de.jaggl.sqlbuilder.domain.ConditionType.WHERE;
import static de.jaggl.sqlbuilder.domain.ConditionType.WHERE_NOT;

import de.jaggl.sqlbuilder.conditions.Condition;
import de.jaggl.sqlbuilder.dialect.Dialect;
import de.jaggl.sqlbuilder.domain.ConditionType;
import de.jaggl.sqlbuilder.domain.Limit;
import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.utils.Indentation;

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
