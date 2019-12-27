package de.jaggl.sqlbuilder.conditions;

import static de.jaggl.sqlbuilder.utils.BuilderUtils.getValued;
import static java.util.stream.Collectors.joining;

import java.text.MessageFormat;
import java.util.Collection;

import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.utils.Indentation;

public class IsNotIn extends Condition
{
    private Object value;
    private Collection<Object> values;

    public IsNotIn(Object value, Collection<Object> values)
    {
        this.value = value;
        this.values = values;
    }

    @Override
    protected String doBuild(BuildingContext context, Indentation indentation)
    {
        return MessageFormat.format(context.getDialect().getLabels().getIsNotIn(), getValued(value, context, indentation), values.stream()
                .map(item -> getValued(item, context, indentation))
                .collect(joining(", ")));
    }
}
