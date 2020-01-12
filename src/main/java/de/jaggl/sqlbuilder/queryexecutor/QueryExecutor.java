package de.jaggl.sqlbuilder.queryexecutor;

import de.jaggl.sqlbuilder.queries.ExecutableQuery;
import de.jaggl.sqlbuilder.queries.Insert;
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

    long execute(UpdatebleQuery updatebleQuery);

    long executeAndReturnKey(Insert insert);

    void execute(ExecutableQuery executableQuery);
}
