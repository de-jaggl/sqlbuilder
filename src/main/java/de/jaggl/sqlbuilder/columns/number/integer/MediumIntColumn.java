package de.jaggl.sqlbuilder.columns.number.integer;

import static java.sql.Types.INTEGER;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@ToString(callSuper = true)
public class MediumIntColumn extends IntegerColumn<MediumIntColumn>
{
    public MediumIntColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition, INTEGER);
    }

    public MediumIntColumn as(String alias)
    {
        return new MediumIntColumn(table, name, alias, columnDefinition);
    }
}
