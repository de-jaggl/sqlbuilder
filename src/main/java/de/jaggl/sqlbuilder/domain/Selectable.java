package de.jaggl.sqlbuilder.domain;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public interface Selectable extends Valuable, Aliasable
{
    public static PlainSelectable plain(String value)
    {
        return new PlainSelectable(value, null);
    }
}
