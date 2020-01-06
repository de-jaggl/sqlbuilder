package de.jaggl.sqlbuilder.queries;

import java.util.List;
import java.util.Optional;

import de.jaggl.sqlbuilder.queryexecutor.SelectQueryExecutor;

/**
 * @author Martin Schumacher
 *
 * @since 2.5.0
 */
public interface QueryableQuery extends Query
{
    default <T> List<T> query(SelectQueryExecutor<T> selectQueryExecutor)
    {
        return selectQueryExecutor.query(this);
    }

    default <T> Optional<T> queryOne(SelectQueryExecutor<T> selectQueryExecutor)
    {
        return selectQueryExecutor.queryOne(this);
    }

    default <T> T queryExactOne(SelectQueryExecutor<T> selectQueryExecutor)
    {
        return selectQueryExecutor.queryExactOne(this);
    }
}
