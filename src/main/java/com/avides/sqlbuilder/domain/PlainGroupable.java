package com.avides.sqlbuilder.domain;

import com.avides.sqlbuilder.utils.Indentation;

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
