package de.jaggl.sqlbuilder.queries;

import static org.powermock.api.easymock.PowerMock.createStrictMock;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.testsupport.QueryExecutorTestSupport;

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
        TABLE.buildCreateTable().execute(queryExecutor);
        verifyAll();
    }
}
