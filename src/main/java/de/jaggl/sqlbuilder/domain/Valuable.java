package de.jaggl.sqlbuilder.domain;

import de.jaggl.sqlbuilder.utils.Indentation;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public interface Valuable
{
    String getValue(BuildingContext context, Indentation indentation);

    public static PlainValuable plain(String value)
    {
        return new PlainValuable(new Plain(value));
    }
}
