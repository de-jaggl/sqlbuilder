package de.jaggl.sqlbuilder.columns;

import static de.jaggl.sqlbuilder.schema.TableBuilderUtil.addColumnToTable;

import de.jaggl.sqlbuilder.schema.Table;
import lombok.AllArgsConstructor;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
@AllArgsConstructor
public abstract class ColumnBuilder<T extends Column>
{
    protected final Table table;
    protected final String name;

    protected abstract T getColumnInstance();

    public T build()
    {
        T columnInstance = getColumnInstance();
        addColumnToTable(columnInstance, table);
        return columnInstance;
    }
}
