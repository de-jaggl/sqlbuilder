package com.avides.sqlbuilder.domain;

import com.avides.sqlbuilder.functions.Function;
import com.avides.sqlbuilder.utils.Indentation;

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
