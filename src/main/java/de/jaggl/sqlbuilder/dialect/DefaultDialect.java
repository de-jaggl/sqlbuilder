package de.jaggl.sqlbuilder.dialect;

import static de.jaggl.sqlbuilder.domain.ConditionType.WHERE_NOT;
import static de.jaggl.sqlbuilder.utils.BuilderUtils.getValued;
import static java.util.stream.Collectors.joining;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import de.jaggl.sqlbuilder.columns.Column;
import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.conditions.CombinedCondition;
import de.jaggl.sqlbuilder.conditions.Condition;
import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.domain.ConditionType;
import de.jaggl.sqlbuilder.domain.Groupable;
import de.jaggl.sqlbuilder.domain.Joinable;
import de.jaggl.sqlbuilder.domain.Limit;
import de.jaggl.sqlbuilder.domain.OrderBy;
import de.jaggl.sqlbuilder.domain.Queryable;
import de.jaggl.sqlbuilder.domain.QueryableSelect;
import de.jaggl.sqlbuilder.domain.Selectable;
import de.jaggl.sqlbuilder.domain.Valuable;
import de.jaggl.sqlbuilder.queries.Delete;
import de.jaggl.sqlbuilder.queries.Insert;
import de.jaggl.sqlbuilder.queries.Select;
import de.jaggl.sqlbuilder.queries.Update;
import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.utils.BuilderUtils;
import de.jaggl.sqlbuilder.utils.Indentation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@Slf4j
public abstract class DefaultDialect implements Dialect
{
    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_DATE;
    static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.nnnnnn");

    private Labels labels = DefaultLabels.getInstance();

    @Override
    public final String build(Insert insert, Indentation indentation)
    {
        log.debug("building insert-statement {} / {}", insert, indentation);
        var context = new BuildingContext(this, indentation.getDelimiter());
        var builder = new StringBuilder(indentation.getIndent());
        appendInsertStatement(builder, insert, context, indentation);
        return build(builder);
    }

    @Override
    public final String build(Update update, Indentation indentation)
    {
        log.debug("building update-statement {} / {}", update, indentation);
        var context = new BuildingContext(this, indentation.getDelimiter());
        var builder = new StringBuilder(indentation.getIndent());
        appendUpdateStatement(builder, update, context, indentation);
        return build(builder);
    }

    @Override
    public final String build(Delete delete, Indentation indentation)
    {
        log.debug("building delete-statement {} / {}", delete, indentation);
        var context = new BuildingContext(this, indentation.getDelimiter());
        var builder = new StringBuilder(indentation.getIndent());
        appendDeleteStatement(builder, delete, context, indentation);
        return build(builder);
    }

    @Override
    public final String build(Select select, Indentation indentation)
    {
        log.debug("building select-statement {} / {}", select, indentation);
        var context = new BuildingContext(this, indentation.getDelimiter());
        var builder = new StringBuilder(indentation.getIndent());
        appendSelectStatement(builder, select, context, indentation);
        return build(builder);
    }

    @Override
    public String buildCreate(Table table, Indentation indentation)
    {
        log.debug("building createTable-statement {} / {}", table, indentation);
        var context = new BuildingContext(this, indentation.getDelimiter());
        var builder = new StringBuilder();
        builder.append(context.getDialect().getLabels().getCreateTable()).append(" ").append(table.getFullName(context));
        builder.append(indentation.getDelimiter()).append("(");
        if (indentation.isEnabled())
        {
            builder.append(indentation.getDelimiter());
        }
        var isFirst = true;
        for (var column : table.getColumns())
        {
            if (!isFirst)
            {
                builder.append(",").append(indentation.getDelimiter());
            }
            builder.append(indentation.indent().getIndent());
            builder.append(BuilderUtils.columnApostrophe(column.getName(), context));
            builder.append(" ");
            builder.append(buildColumnDefinition(column.getColumnDefinition(), context, indentation));
            isFirst = false;
        }
        if (indentation.isEnabled())
        {
            builder.append(indentation.getDelimiter());
        }
        builder.append(")");
        return build(builder);
    }

    String buildColumnDefinition(ColumnDefinition definition, BuildingContext context, Indentation indentation)
    {
        StringBuilder builder = new StringBuilder(definition.getDefinitionName());

        if (definition.getSize() != null)
        {
            builder.append("(").append(definition.getSize().getValue()).append(")");
        }

        if (definition.isUnsigned())
        {
            builder.append(" UNSIGNED");
        }

        if (definition.isNullable())
        {
            if (definition.isDefaultNull())
            {
                builder.append(" DEFAULT NULL");
            }
        }
        else
        {
            builder.append(" NOT NULL");
        }

        if (definition.getDefaultValue() != null)
        {
            builder.append(" DEFAULT ");
            builder.append(getValued(definition.getDefaultValue(), context, indentation));
        }

        if (definition.isAutoIncrement())
        {
            builder.append(" AUTO_INCREMENT");
        }

        return builder.toString();
    }

    protected void appendUpdateStatement(StringBuilder builder, Update update, BuildingContext context, Indentation indentation)
    {
        builder.append(context.getDialect().getLabels().getUpdate()).append(indentation.getDelimiter());
        builder.append(indentation.indent().getIndent()).append(update.getTable().getFullName(context));
        appendAlias(builder, update.getTable().getAlias(), context);
        builder.append(indentation.getDelimiter());
        builder.append(context.getDialect().getLabels().getSet()).append(indentation.getDelimiter());
        appendUpdateValues(builder, update.getValues(), context, indentation.indent());
        appendConditions(context.getDialect().getLabels().getWhere(), builder, update.getWhere(), update.getWhereConditionType(), context, indentation);
    }

    protected void appendInsertStatement(StringBuilder builder, Insert insert, BuildingContext context, Indentation indentation)
    {
        builder.append(context.getDialect().getLabels().getInsertInto()).append(indentation.getDelimiter());
        builder.append(indentation.indent().getIndent()).append(insert.getTable().getFullName(context));
        appendAlias(builder, insert.getTable().getAlias(), context);
        builder.append(indentation.getDelimiter());
        builder.append(context.getDialect().getLabels().getSet()).append(indentation.getDelimiter());
        appendUpdateValues(builder, insert.getValues(), context, indentation.indent());
    }

    protected void appendDeleteStatement(StringBuilder builder, Delete delete, BuildingContext context, Indentation indentation)
    {
        builder.append(context.getDialect().getLabels().getDelete())
                .append(" ")
                .append(context.getDialect().getLabels().getFrom())
                .append(indentation.getDelimiter());
        builder.append(indentation.indent().getIndent()).append(delete.getTable().getFullName(context));
        appendAlias(builder, delete.getTable().getAlias(), context);
        appendConditions(context.getDialect().getLabels().getWhere(), builder, delete.getWhere(), delete.getWhereConditionType(), context, indentation);
        appendLimit(builder, delete.getLimitation(), context, indentation);
    }

    protected void appendSelectStatement(StringBuilder builder, Select select, BuildingContext context, Indentation indentation)
    {
        builder.append(context.getDialect().getLabels().getSelect());
        appendDistinct(builder, select.isDistinct(), context, indentation);
        appendSelectables(builder, select.getSelectables(), context, indentation);
        appendQueryables(builder, select.getFrom(), context, indentation);
        appendJoins(builder, select.getJoins(), context, indentation);
        appendConditions(context.getDialect().getLabels().getWhere(), builder, select.getWhere(), select.getWhereConditionType(), context, indentation);
        appendGrouping(builder, select.getGroupBys(), context, indentation);
        appendConditions(context.getDialect().getLabels().getHaving(), builder, select.getHaving(), select.getHavingConditionType(), context, indentation);
        appendOrdering(builder, select.getOrderBys(), context, indentation);
        appendLimit(builder, select.getLimitation(), context, indentation);
    }

    /**
     * @param builder the {@link StringBuilder} to append the distinct
     * @param isDinstinct whether to append the distinct or not
     * @param context may be used in extending implementation
     * @param indentation may be used in extending implementation
     */
    protected void appendDistinct(StringBuilder builder, boolean isDistinct, BuildingContext context, Indentation indentation)
    {
        if (isDistinct)
        {
            builder.append(" ").append(context.getDialect().getLabels().getDistinct());
        }
    }

    protected void appendSelectables(StringBuilder builder, List<Selectable> selectables, BuildingContext context, Indentation indentation)
    {
        var indent = indentation.indent();
        builder.append(context.getDelimiter());
        if (selectables == null)
        {
            builder.append(indent.getIndent()).append("*");
        }
        else
        {
            builder.append(selectables.stream().map(selectable ->
            {
                var bldr = new StringBuilder(indent.getIndent());
                bldr.append(selectable.getValue(context, indentation));
                appendAlias(bldr, selectable.getAlias(), context);
                return bldr.toString();
            }).collect(joining("," + context.getDelimiter())));
        }
    }

    protected void appendQueryables(StringBuilder builder, Queryable queryable, BuildingContext context, Indentation indentation)
    {
        var fromIndent = QueryableSelect.class.isAssignableFrom(queryable.getClass()) ? indentation : indentation.indent();
        builder.append(context.getDelimiter())
                .append(indentation.getIndent())
                .append(context.getDialect().getLabels().getFrom())
                .append(context.getDelimiter())
                .append(fromIndent.getIndent())
                .append(queryable.getValue(context, fromIndent));
        appendAlias(builder, queryable.getAlias(), context);
    }

    protected void appendJoins(StringBuilder builder, List<Joinable> joins, BuildingContext context, Indentation indentation)
    {
        if (joins != null)
        {
            joins.forEach(join -> builder
                    .append(context.getDelimiter())
                    .append(join.getValue(context, indentation.indent())));
        }
    }

    protected void appendConditions(String keyword, StringBuilder builder, Condition condition, ConditionType whereConditionType, BuildingContext context,
            Indentation indentation)
    {
        if (condition != null)
        {
            builder.append(context.getDelimiter())
                    .append(indentation.getIndent())
                    .append(keyword);
            if (whereConditionType == WHERE_NOT)
            {
                builder.append(" ").append(context.getDialect().getLabels().getNot());
            }
            builder.append(CombinedCondition.class.isAssignableFrom(condition.getClass()) ? context.getDelimiter() : " ")
                    .append(indentation.getIndent())
                    .append(condition.build(context, false, indentation.indent()));
        }
    }

    protected void appendGrouping(StringBuilder builder, List<Groupable> groupBys, BuildingContext context, Indentation indentation)
    {
        if (groupBys != null)
        {
            builder.append(context.getDelimiter())
                    .append(indentation.getIndent())
                    .append(context.getDialect().getLabels().getGroupBy())
                    .append(context.getDelimiter())
                    .append(buildGroupBy(groupBys, context, indentation.indent()));
        }
    }

    protected void appendOrdering(StringBuilder builder, List<OrderBy> orderBys, BuildingContext context, Indentation indentation)
    {
        if (orderBys != null)
        {
            builder.append(context.getDelimiter())
                    .append(indentation.getIndent())
                    .append(context.getDialect().getLabels().getOrderBy())
                    .append(context.getDelimiter())
                    .append(buildOrderBy(orderBys, context, indentation.indent()));
        }
    }

    protected static String buildGroupBy(List<Groupable> groupBys, BuildingContext context, Indentation indentation)
    {
        var builder = new StringBuilder();
        var isFirst = true;
        for (var groupBy : groupBys)
        {
            if (!isFirst)
            {
                builder.append(",");
                builder.append(context.getDelimiter());
            }
            builder.append(indentation.getIndent()).append(groupBy.getValue(context, indentation));
            isFirst = false;
        }
        return builder.toString();
    }

    protected static String buildOrderBy(List<OrderBy> orderBys, BuildingContext context, Indentation indentation)
    {
        var builder = new StringBuilder();
        var isFirst = true;
        for (var orderBy : orderBys)
        {
            if (!isFirst)
            {
                builder.append(",");
                builder.append(context.getDelimiter());
            }
            builder.append(indentation.getIndent()).append(orderBy.getColumn().getFullNameOrAlias(context));
            builder.append(" ").append(orderBy.getDirection().name());
            isFirst = false;
        }
        return builder.toString();
    }

    protected void appendLimit(StringBuilder builder, Limit limit, BuildingContext context, Indentation indentation)
    {
        if (limit != null)
        {
            builder.append(context.getDelimiter()).append(indentation.getIndent()).append(context.getDialect().getLabels().getLimit()).append(" ");
            if (limit.getOffset() > 0)
            {
                builder.append(limit.getOffset()).append(", ");
            }
            builder.append(limit.getLimit());
        }
    }

    protected void appendUpdateValues(StringBuilder builder, Map<Column, Valuable> values, BuildingContext context, Indentation indentation)
    {
        var counter = 0;
        for (var entry : values.entrySet())
        {
            var column = entry.getKey();
            var value = entry.getValue();
            builder.append(indentation.getIndent())
                    .append(column.getFullNameOrAlias(context))
                    .append(" = ")
                    .append(value.getValue(context, indentation));
            if (++counter < values.size())
            {
                builder.append(",").append(indentation.getDelimiter());
            }
        }
    }

    protected void appendAlias(StringBuilder builder, String alias, BuildingContext context)
    {
        if (alias != null)
        {
            builder.append(" ")
                    .append(context.getDialect().getLabels().getAs())
                    .append(" ")
                    .append(BuilderUtils.columnApostrophe(alias, context));
        }
    }

    private static String build(StringBuilder builder)
    {
        var sql = builder.toString();
        log.debug("build sql-statement {}", sql);
        return sql;
    }

    @Override
    public DateTimeFormatter getDateFormatter()
    {
        return DATE_FORMATTER;
    }

    @Override
    public DateTimeFormatter getDateTimeFormatter()
    {
        return DATE_TIME_FORMATTER;
    }

    @Override
    public Labels getLabels()
    {
        return labels;
    }
}
