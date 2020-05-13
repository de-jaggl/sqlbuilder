package de.jaggl.sqlbuilder.core.dialect;

import static java.lang.System.getProperty;

import java.time.format.DateTimeFormatter;

import de.jaggl.sqlbuilder.core.queries.CreateTable;
import de.jaggl.sqlbuilder.core.queries.Delete;
import de.jaggl.sqlbuilder.core.queries.Insert;
import de.jaggl.sqlbuilder.core.queries.Select;
import de.jaggl.sqlbuilder.core.queries.Update;
import de.jaggl.sqlbuilder.core.utils.Indentation;

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

    String build(CreateTable createTable, Indentation indentation);

    DateTimeFormatter getDateFormatter();

    DateTimeFormatter getDateTimeFormatter();

    DateTimeFormatter getTimeFormatter();

    Labels getLabels();

    String escape(String value, char apostrophe);

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
