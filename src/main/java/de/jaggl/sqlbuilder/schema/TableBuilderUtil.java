package de.jaggl.sqlbuilder.schema;

import static lombok.AccessLevel.PRIVATE;

import de.jaggl.sqlbuilder.columns.Column;
import lombok.NoArgsConstructor;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@NoArgsConstructor(access = PRIVATE)
public final class TableBuilderUtil
{
    public static void addColumnToTable(Column column, Table table)
    {
        table.addColumn(column);
    }
}
