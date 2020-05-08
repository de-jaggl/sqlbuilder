package de.jaggl.sqlbuilder.core.domain;

import de.jaggl.sqlbuilder.core.columns.Column;

/**
 * @author Martin Schumacher
 *
 * @since 2.5.0
 */
public class Placeholder extends PlainValuable
{
    private Placeholder(String value)
    {
        super(new Plain(value));
    }

    public static Placeholder placeholder(Column column)
    {
        return new Placeholder(":" + column.getName());
    }

    public static Placeholder placeholder()
    {
        return new Placeholder("?");
    }

    public static Placeholder placeholder(String name)
    {
        return new Placeholder(":" + name);
    }
}
