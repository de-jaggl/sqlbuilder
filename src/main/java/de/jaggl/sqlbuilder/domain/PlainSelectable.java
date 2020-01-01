package de.jaggl.sqlbuilder.domain;

import de.jaggl.sqlbuilder.utils.Indentation;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
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
