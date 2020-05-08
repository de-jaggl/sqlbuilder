package de.jaggl.sqlbuilder.core.queries;

import de.jaggl.sqlbuilder.core.queryexecutor.QueryExecutor;

/**
 * @author Martin Schumacher
 *
 * @since 2.5.0
 */
public interface UpdatebleQuery extends Query
{
    default long execute(QueryExecutor queryExecutor)
    {
        return queryExecutor.execute(this);
    }
}
