package de.jaggl.sqlbuilder.core.domain;

import de.jaggl.sqlbuilder.core.utils.Indentation;

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
