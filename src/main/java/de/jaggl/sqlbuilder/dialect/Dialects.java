package de.jaggl.sqlbuilder.dialect;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import de.jaggl.sqlbuilder.domain.exceptions.UnknownDialectException;

/**
 * @author Martin Schumacher
 *
 * @since 2.0.0
 */
public final class Dialects
{
    private static final Map<String, Dialect> REGISTERED_DIALECTS = new HashMap<>();

    private Dialects()
    {
        // private constructor to hide the public one
    }

    static void register(Dialect dialect)
    {
        REGISTERED_DIALECTS.put(dialect.getName().toLowerCase(), dialect);
    }

    static void unregister(String name)
    {
        REGISTERED_DIALECTS.remove(name.toLowerCase());
    }

    static Dialect forName(String name)
    {
        Dialect dialect = REGISTERED_DIALECTS.get(name.toLowerCase());
        if (dialect == null)
        {
            throw new UnknownDialectException(MessageFormat.format("no dialect registered for name ''{0}''", name));
        }
        return dialect;
    }

    public static final Dialect MYSQL = MySqlDialect.getInstance();
    public static final Dialect SYBASE = SybaseDialect.getInstance();
}
