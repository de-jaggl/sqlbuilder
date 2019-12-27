package de.jaggl.sqlbuilder.domain;

public interface Selectable extends Valuable, Aliasable
{
    public static PlainSelectable plain(String value)
    {
        return new PlainSelectable(value, null);
    }
}
