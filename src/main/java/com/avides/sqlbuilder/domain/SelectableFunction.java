package com.avides.sqlbuilder.domain;

import com.avides.sqlbuilder.functions.Function;
import com.avides.sqlbuilder.utils.Indentation;

public class SelectableFunction implements Selectable
{
    private Function function;
    private String alias;

    public SelectableFunction(Function function, String alias)
    {
        this.function = function;
        this.alias = alias;
    }

    @Override
    public String getValue(BuildingContext context, Indentation indentation)
    {
        return function.getValue(context, indentation);
    }

    @Override
    public String getAlias()
    {
        return alias;
    }
}
