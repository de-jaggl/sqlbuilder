package de.jaggl.sqlbuilder.conditions;

import static de.jaggl.sqlbuilder.utils.BuilderUtils.getValued;
import static java.util.stream.Collectors.joining;

import java.text.MessageFormat;
import java.util.Collection;

import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.domain.Placeholder;
import de.jaggl.sqlbuilder.utils.Indentation;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@RequiredArgsConstructor
@ToString(callSuper = true)
public class IsIn extends Condition
{
    private final Object value;
    private final Collection<Object> values;

    private Placeholder placeholder;

    public IsIn(Object value, Placeholder placeholder)
    {
        this.value = value;
        this.values = null;
        this.placeholder = placeholder;
        addPlaceholderSqlTypes(resolvePlaceholderSqlTypes(value, placeholder));
    }

    @Override
    protected String doBuild(BuildingContext context, Indentation indentation)
    {
        if (values != null)
        {
            return MessageFormat.format(context.getDialect().getLabels().getIsIn(), getValued(value, context, indentation), values.stream()
                    .map(item -> getValued(item, context, indentation))
                    .collect(joining(", ")));
        }
        return MessageFormat
                .format(context.getDialect().getLabels().getIsIn(), getValued(value, context, indentation), getValued(placeholder, context, indentation));
    }
}
