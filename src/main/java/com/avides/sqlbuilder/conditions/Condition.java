package com.avides.sqlbuilder.conditions;

import static com.avides.sqlbuilder.domain.ConcatenationType.AND;
import static com.avides.sqlbuilder.domain.ConcatenationType.OR;
import static com.avides.sqlbuilder.domain.ConditionType.WHERE;
import static com.avides.sqlbuilder.domain.ConditionType.WHERE_NOT;

import com.avides.sqlbuilder.domain.BuildingContext;
import com.avides.sqlbuilder.domain.ConcatenationType;
import com.avides.sqlbuilder.domain.ConditionType;
import com.avides.sqlbuilder.utils.Indentation;

public abstract class Condition
{
    private ConditionType type;
    private ConcatenationType concatenation;

    protected abstract String doBuild(BuildingContext context, Indentation indentation);

    public String build(BuildingContext context, boolean concatenate, Indentation indentation)
    {
        var builder = new StringBuilder();
        if (concatenate)
        {
            builder.append(concatenation == AND ? context.getDialect().getLabels().getAnd() : context.getDialect().getLabels().getOr());
        }
        if (type == WHERE_NOT)
        {
            builder.append(" ").append(context.getDialect().getLabels().getNot());
        }
        if (concatenate)
        {
            if (indentation.isEnabled() && CombinedCondition.class.isAssignableFrom(getClass()))
            {
                builder.append(context.getDelimiter());
            }
            else
            {
                builder.append(" ");
            }
        }
        builder.append(doBuild(context, indentation));
        return builder.toString();
    }

    public ConditionType getType()
    {
        return type;
    }

    public void setType(ConditionType type)
    {
        this.type = type;
    }

    public ConcatenationType getConcatenation()
    {
        return concatenation;
    }

    public void setConcatenation(ConcatenationType concatenation)
    {
        this.concatenation = concatenation;
    }

    public Condition and(Condition other)
    {
        return append(other, AND, WHERE);
    }

    public Condition or(Condition other)
    {
        return append(other, OR, WHERE);
    }

    public Condition andNot(Condition other)
    {
        return append(other, AND, WHERE_NOT);
    }

    public Condition orNot(Condition other)
    {
        return append(other, OR, WHERE_NOT);
    }

    public Condition append(Condition other, ConcatenationType concatenationType, ConditionType conditionType)
    {
        other.setConcatenation(concatenationType);
        other.setType(conditionType);
        CombinedCondition combined;
        if (!CombinedCondition.class.isAssignableFrom(getClass()))
        {
            combined = new CombinedCondition(this);
        }
        else
        {
            combined = (CombinedCondition) this;
        }
        combined.append(other);
        return combined;
    }

    public static Condition plain(String plainCondition)
    {
        return new PlainCondition(plainCondition);
    }
}
