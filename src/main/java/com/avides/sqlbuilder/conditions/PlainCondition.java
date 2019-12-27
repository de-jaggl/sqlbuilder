package com.avides.sqlbuilder.conditions;

import com.avides.sqlbuilder.domain.BuildingContext;
import com.avides.sqlbuilder.utils.Indentation;

public class PlainCondition extends Condition
{
    private String value;

    public PlainCondition(String value)
    {
        this.value = value;
    }

    @Override
    protected String doBuild(BuildingContext context, Indentation indentation)
    {
        return value;
    }
}
