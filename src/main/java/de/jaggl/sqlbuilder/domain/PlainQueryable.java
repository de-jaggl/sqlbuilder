package de.jaggl.sqlbuilder.domain;

import de.jaggl.sqlbuilder.utils.Indentation;

public class PlainQueryable implements Queryable
{
    private String value;
    private String alias;

    public PlainQueryable(String value, String alias)
    {
        this.alias = alias;
        this.value = value;
    }

    @Override
    public String getValue(BuildingContext context, Indentation indentation)
    {
        return value;
    }

    @Override
    public String getAlias()
    {
        return alias;
    }

    public Queryable as(@SuppressWarnings("hiding") String alias)
    {
        return new PlainQueryable(value, alias);
    }
}
