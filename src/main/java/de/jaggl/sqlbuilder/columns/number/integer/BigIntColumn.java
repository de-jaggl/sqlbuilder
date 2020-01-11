package de.jaggl.sqlbuilder.columns.number.integer;

import static java.sql.Types.BIGINT;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@ToString(callSuper = true)
public class BigIntColumn extends IntegerColumn<BigIntColumn>
{
    public BigIntColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition, BIGINT);
    }

    public BigIntColumn as(String alias)
    {
        return new BigIntColumn(table, name, alias, columnDefinition);
    }
}
