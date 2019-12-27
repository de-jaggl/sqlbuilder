package com.avides.sqlbuilder.domain;

import com.avides.sqlbuilder.columns.Column;
import com.avides.sqlbuilder.utils.Indentation;

public class ValuableColumn implements Valuable
{
    private Column column;

    public ValuableColumn(Column column)
    {
        this.column = column;
    }

    @Override
    public String getValue(BuildingContext context, Indentation indentation)
    {
        return column.getFullNameOrAlias(context);
    }
}
