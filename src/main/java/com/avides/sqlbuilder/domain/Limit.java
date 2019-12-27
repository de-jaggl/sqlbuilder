package com.avides.sqlbuilder.domain;

public class Limit
{
    private long value;
    private long offset;

    public Limit(long limit, long offset)
    {
        value = limit;
        this.offset = offset;
    }

    public long getLimit()
    {
        return value;
    }

    public long getOffset()
    {
        return offset;
    }
}
