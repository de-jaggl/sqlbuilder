package de.jaggl.sqlbuilder.core.queries;

import de.jaggl.sqlbuilder.core.queryexecutor.QueryExecutor;

/**
 * @author Martin Schumacher
 *
 * @since 2.5.0
 */
public interface ExecutableQuery extends Query
{
    default void execute(QueryExecutor queryExecutor)
    {
        queryExecutor.execute(this);
    }
}
