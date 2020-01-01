package de.jaggl.sqlbuilder.domain;

import de.jaggl.sqlbuilder.utils.Indentation;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public class PlainGroupable implements Groupable
{
    private String value;

    public PlainGroupable(String value)
    {
        this.value = value;
    }

    @Override
    public String getValue(BuildingContext context, Indentation indentation)
    {
        return value;
    }
}
