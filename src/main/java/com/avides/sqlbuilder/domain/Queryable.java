package com.avides.sqlbuilder.domain;

public interface Queryable extends Valuable, Aliasable
{
    public static PlainQueryable plain(String value)
    {
        return new PlainQueryable(value, null);
    }
}
