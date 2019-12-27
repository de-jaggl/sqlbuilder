package de.jaggl.sqlbuilder.conditions;

import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.utils.Indentation;

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
