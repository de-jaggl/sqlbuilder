package de.jaggl.sqlbuilder.core.queryexecutor;

import de.jaggl.sqlbuilder.core.queries.ExecutableQuery;
import de.jaggl.sqlbuilder.core.queries.Insert;
import de.jaggl.sqlbuilder.core.queries.UpdatebleQuery;

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
