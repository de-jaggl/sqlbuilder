package com.avides.sqlbuilder.dialect;

import java.time.format.DateTimeFormatter;

import com.avides.sqlbuilder.queries.Delete;
import com.avides.sqlbuilder.queries.Insert;
import com.avides.sqlbuilder.queries.Select;
import com.avides.sqlbuilder.queries.Update;
import com.avides.sqlbuilder.schema.Table;
import com.avides.sqlbuilder.utils.Indentation;

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
}
