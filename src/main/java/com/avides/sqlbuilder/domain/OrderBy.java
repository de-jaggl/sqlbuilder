package com.avides.sqlbuilder.domain;

import com.avides.sqlbuilder.columns.Column;

public class OrderBy
{
    private Column column;
    private OrderDirection direction;

    public OrderBy(Column column, OrderDirection direction)
    {
        this.column = column;
        this.direction = direction;
    }

    public Column getColumn()
    {
        return column;
    }

    public OrderDirection getDirection()
    {
        return direction;
    }
}
