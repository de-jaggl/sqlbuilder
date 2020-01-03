package de.jaggl.sqlbuilder.domain;

import de.jaggl.sqlbuilder.conditions.CombinedCondition;
import de.jaggl.sqlbuilder.conditions.Condition;
import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.utils.BuilderUtils;
import de.jaggl.sqlbuilder.utils.Indentation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@AllArgsConstructor
@ToString
public class JoinableTable implements Joinable
{
    private JoinType joinType;

    @Getter
    private Table table;

    @Getter
    private Condition condition;

    @Override
    public String getValue(BuildingContext context, Indentation indentation)
    {
        return buildJoin(joinType, table.getFullName(context), table.getAlias(), condition, context, indentation);
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
