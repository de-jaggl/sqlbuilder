package de.jaggl.sqlbuilder.domain;

import de.jaggl.sqlbuilder.queries.Select;
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
public class QueryableSelect implements Queryable
{
    private Select select;

    @Getter
    private String alias;

    @Override
    public String getValue(BuildingContext context, Indentation indentation)
    {
        var builder = new StringBuilder();
        builder.append("(");
        if (indentation.isEnabled())
        {
            builder.append(context.getDelimiter());
        }
        builder.append(select.build(context.getDialect(), indentation.indent()));
        if (indentation.isEnabled())
        {
            builder.append(context.getDelimiter()).append(indentation.deIndent().getIndent());
        }
        builder.append(")");
        return builder.toString();
    }
}
