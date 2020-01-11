package de.jaggl.sqlbuilder.columns.string;

import static java.sql.Types.VARCHAR;

import de.jaggl.sqlbuilder.columns.ColumnDefinition;
import de.jaggl.sqlbuilder.schema.Table;
import lombok.ToString;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@ToString(callSuper = true)
public class VarCharColumn extends StringColumn<VarCharColumn>
{
    VarCharColumn(Table table, String name, String alias, ColumnDefinition columnDefinition)
    {
        super(table, name, alias, columnDefinition, VARCHAR);
    }

    public VarCharColumn as(String alias)
    {
        return new VarCharColumn(getTable(), getName(), alias, columnDefinition);
    }
}
