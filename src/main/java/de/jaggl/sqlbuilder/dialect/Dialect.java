package de.jaggl.sqlbuilder.dialect;

import static java.lang.System.getProperty;

import java.time.format.DateTimeFormatter;

import de.jaggl.sqlbuilder.queries.Delete;
import de.jaggl.sqlbuilder.queries.Insert;
import de.jaggl.sqlbuilder.queries.Select;
import de.jaggl.sqlbuilder.queries.Update;
import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.utils.Indentation;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public interface Dialect
{
    String getName();

    String build(Insert insert, Indentation indentation);

    String build(Update update, Indentation indentation);

    String build(Delete delete, Indentation indentation);

    String build(Select select, Indentation indentation);

    String buildCreate(Table configuredTable, Indentation indentation);

    DateTimeFormatter getDateFormatter();

    DateTimeFormatter getDateTimeFormatter();

    Labels getLabels();

    public static void register(Dialect dialect)
    {
        Dialects.register(dialect);
    }

    public static void unregister(String name)
    {
        Dialects.unregister(name);
    }

    public static Dialect forName(String name)
    {
        return Dialects.forName(name);
    }

    public static Dialect getDefault()
    {
        return forName(getProperty("sqlbuilder.defaultDialect", "MYSQL"));
    }
}
