package de.jaggl.sqlbuilder.core.queries;

import static de.jaggl.sqlbuilder.core.queries.Queries.createTable;
import static org.powermock.api.easymock.PowerMock.createStrictMock;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.core.schema.Table;
import de.jaggl.sqlbuilder.core.testsupport.QueryExecutorTestSupport;

class ExecutableQueryTest extends QueryExecutorTestSupport
{
    private static final Table TABLE = Table.create("table");

    @Test
    void testQuery()
    {
        var runnable = createStrictMock(Runnable.class);
        var queryExecutor = new MyQueryExecutor(runnable);

        runnable.run();

        replayAll();
        createTable(TABLE).execute(queryExecutor);
        verifyAll();
    }
}
