package de.jaggl.sqlbuilder.domain;

import de.jaggl.sqlbuilder.functions.Function;
import de.jaggl.sqlbuilder.utils.Indentation;

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
