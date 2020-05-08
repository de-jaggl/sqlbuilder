package de.jaggl.sqlbuilder.core.conditions;

import de.jaggl.sqlbuilder.core.domain.BuildingContext;
import de.jaggl.sqlbuilder.core.utils.Indentation;

public class EmptyCondition extends Condition
{
    @Override
    protected String doBuild(BuildingContext context, Indentation indentation)
    {
        throw new UnsupportedOperationException("the EmptyCondition is not meant to be build");
    }
}
