package com.avides.sqlbuilder.domain;

import com.avides.sqlbuilder.utils.Indentation;

public class PlainSelectable implements Selectable
{
    private String value;
    private String alias;

    public PlainSelectable(String value, String alias)
    {
        this.value = value;
        this.alias = alias;
    }

    public Selectable as(@SuppressWarnings("hiding") String alias)
    {
        return new PlainSelectable(value, alias);
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
}
