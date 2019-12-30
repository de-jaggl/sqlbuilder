package de.jaggl.sqlbuilder.queries;

import de.jaggl.sqlbuilder.domain.Selectable;
import de.jaggl.sqlbuilder.schema.Table;

public interface Queries
{
    public static Insert insertInto(Table table)
    {
        return new Insert().into(table);
    }

    public static Delete deleteFrom(Table table)
    {
        return new Delete().from(table);
    }

    public static Update update(Table table)
    {
        return new Update(table);
    }

    public static Select select(Selectable... selectables)
    {
        return new Select(selectables);
    }

    public static Select selectDistinct(Selectable... selectables)
    {
        return new Select(selectables).distinct();
    }
}
