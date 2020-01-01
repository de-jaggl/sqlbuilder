package de.jaggl.sqlbuilder.domain;

import de.jaggl.sqlbuilder.columns.Column;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
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
