package de.jaggl.sqlbuilder.domain;

import de.jaggl.sqlbuilder.utils.BuilderUtils;
import de.jaggl.sqlbuilder.utils.Indentation;

public class PlainValuable implements Valuable
{
    private Object value;

    public PlainValuable(Object plainValue)
    {
        this.value = plainValue;
    }

    @Override
    public String getValue(BuildingContext context, Indentation indentation)
    {
        return BuilderUtils.getValued(value, context, indentation);
    }
}
