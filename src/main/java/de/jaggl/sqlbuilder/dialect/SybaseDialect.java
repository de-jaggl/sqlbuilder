package de.jaggl.sqlbuilder.dialect;

import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.domain.Limit;
import de.jaggl.sqlbuilder.queries.Delete;
import de.jaggl.sqlbuilder.queries.Select;
import de.jaggl.sqlbuilder.utils.Indentation;

public class SybaseDialect extends DefaultDialect
{
    private static final SybaseDialect instance;

    static
    {
        instance = new SybaseDialect();
        Dialect.register(instance);
    }

    private SybaseDialect()
    {
        // private constructor to hide the public one
    }

    @Override
    public String getName()
    {
        return "Sybase";
    }

    @Override
    protected void appendSelectStatement(StringBuilder builder, Select select, BuildingContext context, Indentation indentation)
    {
        builder.append(context.getDialect().getLabels().getSelect());
        appendLimit(builder, select.getLimitation(), context, indentation);
        appendDistinct(builder, select.isDistinct(), context, indentation);
        appendSelectables(builder, select.getSelectables(), context, indentation);
        appendQueryables(builder, select.getFrom(), context, indentation);
        appendJoins(builder, select.getJoins(), context, indentation);
        appendConditions(context.getDialect().getLabels().getWhere(), builder, select.getWhere(), select.getWhereConditionType(), context, indentation);
        appendGrouping(builder, select.getGroupBys(), context, indentation);
        appendConditions(context.getDialect().getLabels().getHaving(), builder, select.getHaving(), select.getHavingConditionType(), context, indentation);
        appendOrdering(builder, select.getOrderBys(), context, indentation);
    }

    @Override
    protected void appendDeleteStatement(StringBuilder builder, Delete delete, BuildingContext context, Indentation indentation)
    {
        builder.append(context.getDialect().getLabels().getDelete());
        appendLimit(builder, delete.getLimitation(), context, indentation);
        if (delete.getLimitation() != null)
        {
            builder.append(" ");
        }
        builder.append(context.getDialect().getLabels().getFrom()).append(indentation.getDelimiter());
        builder.append(indentation.indent().getIndent()).append(delete.getTable().getFullName(context));
        appendConditions(context.getDialect().getLabels().getWhere(), builder, delete.getWhere(), delete.getWhereConditionType(), context, indentation);
    }

    @Override
    protected void appendLimit(StringBuilder builder, Limit limit, BuildingContext context, Indentation indentation)
    {
        if (limit != null)
        {
            builder.append(" TOP ").append(limit.getLimit());
            if (limit.getOffset() > 0)
            {
                builder.append(" START AT ").append(limit.getOffset() + 1);
            }
        }
    }

    public static SybaseDialect getInstance()
    {
        return instance;
    }
}
