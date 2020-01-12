package de.jaggl.sqlbuilder.queries;

import de.jaggl.sqlbuilder.queryexecutor.QueryExecutor;

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
