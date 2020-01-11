package de.jaggl.sqlbuilder.functions;

import de.jaggl.sqlbuilder.columns.Column;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@ToString(callSuper = true)
public class Sum extends NumberColumnFunction
{
    public Sum(Column column)
    {
        super(column, "SUM");
    }

    public Sum(Column column, String alias)
    {
        super(column, "SUM", alias);
    }

    public Sum as(String alias)
    {
        return new Sum(column, alias);
    }

    @Override
    public int getSqlType()
    {
        return column.getSqlType();
    }
}
