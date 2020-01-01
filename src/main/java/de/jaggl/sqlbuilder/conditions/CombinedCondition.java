package de.jaggl.sqlbuilder.conditions;

import java.util.ArrayList;
import java.util.List;

import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.utils.Indentation;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class CombinedCondition extends Condition
{
    private List<Condition> conditions = new ArrayList<>();

    private CombinedCondition()
    {
        // nothing to do here
    }

    public CombinedCondition(Condition condition)
    {
        this.conditions.add(condition);
    }

    public List<Condition> getConditions()
    {
        return conditions;
    }

    public void append(Condition condition)
    {
        conditions.add(condition);
    }

    @Override
    protected String doBuild(BuildingContext context, Indentation indentation)
    {
        var builder = new StringBuilder();
        builder.append(indentation.deIndent().getIndent()).append("(");
        if (indentation.isEnabled())
        {
            builder.append(context.getDelimiter());
        }
        var isFirst = true;
        for (var condition : conditions)
        {
            if (!isFirst)
            {
                builder.append(context.getDelimiter());
            }
            builder.append(indentation.getIndent()).append(condition.build(context, !isFirst, indentation.indent()));
            isFirst = false;
        }
        if (indentation.isEnabled())
        {
            builder.append(context.getDelimiter()).append(indentation.deIndent().getIndent());
        }
        builder.append(")");
        return builder.toString();
    }

    public static Condition getCopy(Condition condition)
    {
        if (condition == null)
        {
            return null;
        }
        if (CombinedCondition.class.isAssignableFrom(condition.getClass()))
        {
            var conditions = new ArrayList<Condition>();
            for (var subCondition : ((CombinedCondition) condition).getConditions())
            {
                conditions.add(getCopy(subCondition));
            }
            var copy = new CombinedCondition();
            copy.conditions = conditions;
            copy.setType(condition.getType());
            copy.setConcatenation(condition.getConcatenation());
            return copy;
        }
        return condition;
    }
}
