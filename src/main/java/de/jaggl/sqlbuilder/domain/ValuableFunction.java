package de.jaggl.sqlbuilder.domain;

import de.jaggl.sqlbuilder.functions.Function;
import de.jaggl.sqlbuilder.utils.Indentation;

public class ValuableFunction implements Valuable
{
    private Function function;

    public ValuableFunction(Function function)
    {
        this.function = function;
    }

    @Override
    public String getValue(BuildingContext context, Indentation indentation)
    {
        return function.getValue(context, indentation);
    }
}
