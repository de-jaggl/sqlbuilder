package de.jaggl.sqlbuilder.testsupport;

import de.jaggl.sqlbuilder.queries.ExecutableQuery;
import de.jaggl.sqlbuilder.queries.Insert;
import de.jaggl.sqlbuilder.queries.UpdatebleQuery;
import de.jaggl.sqlbuilder.queryexecutor.QueryExecutor;
import de.jaggl.sqlbuilder.queryexecutor.RowExtractor;
import de.jaggl.sqlbuilder.queryexecutor.SelectQueryExecutor;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class QueryExecutorTestSupport
{
    @AllArgsConstructor
    @NoArgsConstructor
    protected class MyQueryExecutor implements QueryExecutor
    {
        private Runnable runnable;

        @Override
        public <T> SelectQueryExecutor<T> select(RowExtractor<T> rowMapper)
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public <T> SelectQueryExecutor<T> select(Class<T> elementType)
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public long execute(UpdatebleQuery updatebleQuery)
        {
            return 5;
        }

        @Override
        public long executeAndReturnKey(Insert insert)
        {
            return 3;
        }

        @Override
        public void execute(ExecutableQuery executableQuery)
        {
            runnable.run();
        }
    }
}
