package com.avides.sqlbuilder.domain;

public class IntSize implements Size
{
    private final int value;

    private IntSize(int value)
    {
        this.value = value;
    }

    @Override
    public String getValue()
    {
        return Integer.toString(value);
    }

    public static IntSize valueOf(Integer size)
    {
        return size != null ? new IntSize(size.intValue()) : null;
    }
}
