package de.jaggl.sqlbuilder.queryexecutor;

import java.util.List;
import java.util.Optional;

import de.jaggl.sqlbuilder.queries.Query;

/**
 * @author Martin Schumacher
 *
 * @since 2.5.0
 */
public interface SelectQueryExecutor<T>
{
    List<T> query(Query select);

    Optional<T> queryOne(Query select);

    T queryExactOne(Query query);
}
