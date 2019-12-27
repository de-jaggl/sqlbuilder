package de.jaggl.sqlbuilder.domain;

import de.jaggl.sqlbuilder.queries.Select;
import de.jaggl.sqlbuilder.utils.Indentation;

public class QueryableSelect implements Queryable
{
    private Select select;
    private String alias;

    public QueryableSelect(Select select, String alias)
    {
        this.select = select;
        this.alias = alias;
    }

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

    @Override
    public String getAlias()
    {
        return alias;
    }
}
