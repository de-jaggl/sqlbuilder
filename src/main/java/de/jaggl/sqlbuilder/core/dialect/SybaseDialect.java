package de.jaggl.sqlbuilder.core.dialect;

import static de.jaggl.sqlbuilder.core.utils.BuilderUtils.columnApostrophe;

import java.util.Map;

import de.jaggl.sqlbuilder.core.columns.Column;
import de.jaggl.sqlbuilder.core.domain.BuildingContext;
import de.jaggl.sqlbuilder.core.domain.Limit;
import de.jaggl.sqlbuilder.core.domain.Valuable;
import de.jaggl.sqlbuilder.core.domain.ValuableColumn;
import de.jaggl.sqlbuilder.core.queries.Delete;
import de.jaggl.sqlbuilder.core.queries.Insert;
import de.jaggl.sqlbuilder.core.queries.Select;
import de.jaggl.sqlbuilder.core.utils.Indentation;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
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
    public String escape(String value, char apostrophe)
    {
        return value.replace(String.valueOf(apostrophe), String.valueOf(apostrophe) + String.valueOf(apostrophe));
    }

    @Override
    protected void appendInsertStatement(StringBuilder builder, Insert insert, BuildingContext context, Indentation indentation)
    {
        builder.append(context.getDialect().getLabels().getInsertInto()).append(" ").append(insert.getTable().getFullName(context));
        builder.append(indentation.getDelimiter());
        appendInsertColumns(builder, insert.getValues(), context, indentation.indent());
        appendInsertValues(builder, insert.getValues(), context, indentation.indent());
    }

    protected void appendInsertColumns(StringBuilder builder, Map<Column, Valuable> values, BuildingContext context, Indentation indentation)
    {
        var counter = 0;
        builder.append(indentation.getIndent()).append("(");
        for (var entry : values.entrySet())
        {
            var column = entry.getKey();
            builder.append(columnApostrophe(column.getName(), context));
            if (++counter < values.size())
            {
                builder.append(", ");
            }
        }
        builder.append(")").append(indentation.getDelimiter());
    }

    protected void appendInsertValues(StringBuilder builder, Map<Column, Valuable> values, BuildingContext context, Indentation indentation)
    {
        var counter = 0;
        builder.append("VALUES").append(indentation.getDelimiter());
        builder.append(indentation.getIndent()).append("(");
        for (var entry : values.entrySet())
        {
            var value = entry.getValue();
            if (ValuableColumn.class.isAssignableFrom(value.getClass()))
            {
                builder.append(columnApostrophe(((ValuableColumn) value).getName(), context));
            }
            else
            {
                builder.append(value.getValue(context, indentation));
            }
            if (++counter < values.size())
            {
                builder.append(", ");
            }
        }
        builder.append(")");
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
        if (delete.getLimitation() == null)
        {
            builder.append(" ");
        }
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
