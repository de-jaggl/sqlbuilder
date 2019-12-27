package com.avides.sqlbuilder.domain;

import com.avides.sqlbuilder.conditions.CombinedCondition;
import com.avides.sqlbuilder.conditions.Condition;
import com.avides.sqlbuilder.schema.Table;
import com.avides.sqlbuilder.utils.BuilderUtils;
import com.avides.sqlbuilder.utils.Indentation;

public class JoinableTable implements Joinable
{
    private JoinType joinType;
    private Table table;
    private Condition condition;

    public JoinableTable(JoinType joinType, Table table, Condition condition)
    {
        this.joinType = joinType;
        this.table = table;
        this.condition = condition;
    }

    @Override
    public String getValue(BuildingContext context, Indentation indentation)
    {
        return buildJoin(joinType, table.getFullName(context), table.getAlias(), condition, context, indentation);
    }

    public Table getTable()
    {
        return table;
    }

    public Condition getCondition()
    {
        return condition;
    }

    private static String buildJoin(JoinType joinType, String plainJoinable, String alias, Condition condition, BuildingContext context,
            Indentation indentation)
    {
        var builder = new StringBuilder();
        if (joinType != null)
        {
            builder.append(joinType.getValue()).append(" ");
        }
        builder.append("JOIN ")
                .append(plainJoinable);
        if (alias != null)
        {
            builder.append(" AS ").append(BuilderUtils.columnApostrophe(alias, context));
        }
        builder.append(" ON")
                .append(CombinedCondition.class.isAssignableFrom(condition.getClass()) ? context.getDelimiter() : " ")
                .append(condition.build(context, false, indentation));
        return builder.toString();
    }
}
