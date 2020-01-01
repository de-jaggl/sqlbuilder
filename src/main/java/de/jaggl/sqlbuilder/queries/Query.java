package de.jaggl.sqlbuilder.queries;

import static de.jaggl.sqlbuilder.utils.Indentation.disabled;

import de.jaggl.sqlbuilder.dialect.Dialect;
import de.jaggl.sqlbuilder.utils.Indentation;

public interface Query
{
    String build(Dialect dialect, Indentation indentation);

    default String build(Dialect dialect)
    {
        return build(dialect, disabled());
    }

    default String build(String dialectName)
    {
        return build(Dialect.forName(dialectName), Indentation.disabled());
    }

    default String build(String dialectName, Indentation indentation)
    {
        return build(Dialect.forName(dialectName), indentation);
    }
}
