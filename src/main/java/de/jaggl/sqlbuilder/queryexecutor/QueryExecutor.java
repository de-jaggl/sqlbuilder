package de.jaggl.sqlbuilder.queryexecutor;

import de.jaggl.sqlbuilder.queries.ExecutableQuery;
import de.jaggl.sqlbuilder.queries.UpdatebleQuery;

/**
 * @author Martin Schumacher
 *
 * @since 2.5.0
 */
public interface QueryExecutor
{
    <T> SelectQueryExecutor<T> select(RowExtractor<T> rowMapper);

    <T> SelectQueryExecutor<T> select(Class<T> elementType);

    int execute(UpdatebleQuery updatebleQuery);

    void execute(ExecutableQuery executableQuery);
}
