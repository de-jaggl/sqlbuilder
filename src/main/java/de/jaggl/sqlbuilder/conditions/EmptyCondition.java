package de.jaggl.sqlbuilder.conditions;

import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.utils.Indentation;

public class EmptyCondition extends Condition
{
    @Override
    protected String doBuild(BuildingContext context, Indentation indentation)
    {
        throw new UnsupportedOperationException("the EmptyCondition is not meant to be build");
    }
}
