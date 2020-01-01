package de.jaggl.sqlbuilder.domain;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public interface Queryable extends Valuable, Aliasable
{
    public static PlainQueryable plain(String value)
    {
        return new PlainQueryable(value, null);
    }
}
