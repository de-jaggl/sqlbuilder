package de.jaggl.sqlbuilder.queries;

import de.jaggl.sqlbuilder.domain.Selectable;
import de.jaggl.sqlbuilder.schema.Table;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public interface Queries
{
    /**
     * Creates an {@link Insert} for the given {@link Table}
     * 
     * @param table the {@link Table} to create the {@link Insert} for
     * @return the created {@link Insert}
     */
    public static Insert insertInto(Table table)
    {
        return new Insert().into(table);
    }

    /**
     * Creates a {@link Delete} for the given {@link Table}
     * 
     * @param table the {@link Table} to create the {@link Delete} for
     * @return the created {@link Delete}
     */
    public static Delete deleteFrom(Table table)
    {
        return new Delete().from(table);
    }

    /**
     * Creates an {@link Update} for the given {@link Table}
     * 
     * @param table the {@link Table} to create the {@link Update} for
     * @return the created {@link Update}
     */
    public static Update update(Table table)
    {
        return new Update(table);
    }

    /**
     * Creates a {@link Select} with the given {@link Selectable}s
     * 
     * @param selectables the {@link Selectable}s to create the {@link Select} for
     * @return the created {@link Select}
     */
    public static Select select(Selectable... selectables)
    {
        return new Select(selectables);
    }

    /**
     * Creates a {@link Select} with the given {@link Selectable}s and the DISTINCT-keyword
     * 
     * @param selectables the {@link Selectable}s to create the {@link Select} for
     * @return the created {@link Select}
     */
    public static Select selectDistinct(Selectable... selectables)
    {
        return new Select(selectables).distinct();
    }
}
