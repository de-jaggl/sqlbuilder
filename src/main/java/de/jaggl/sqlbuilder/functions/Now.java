package de.jaggl.sqlbuilder.functions;

import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.utils.Indentation;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class Now implements Function
{
    private String alias;

    public Now()
    {
        // nothing to do here so far
    }

    public Now(String alias)
    {
        this.alias = alias;
    }

    public Now as(@SuppressWarnings("hiding") String alias)
    {
        return new Now(alias);
    }

    @Override
    public String getValue(BuildingContext context, Indentation indentation)
    {
        return "NOW()";
    }

    @Override
    public String getAlias()
    {
        return alias;
    }
}
