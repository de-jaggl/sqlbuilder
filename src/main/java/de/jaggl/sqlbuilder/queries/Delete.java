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

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class Delete implements Query
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
        return limit(limit, 0);
    }

    @Override
    public String build(Dialect dialect, Indentation indentation)
    {
        return dialect.build(this, indentation);
    }

    public static void clearWheres(Delete delete)
    {
        delete.where = null;
    }

    public static void clearLimit(Delete delete)
    {
        delete.limitation = null;
    }

    public static Delete copy(Delete delete)
    {
        return new Delete(delete);
    }
}
