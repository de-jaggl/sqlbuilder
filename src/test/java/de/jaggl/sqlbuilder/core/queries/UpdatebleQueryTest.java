package de.jaggl.sqlbuilder.core.queries;

import static de.jaggl.sqlbuilder.core.queries.Queries.insertInto;
import static de.jaggl.sqlbuilder.core.queries.Queries.update;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.core.columns.string.VarCharColumn;
import de.jaggl.sqlbuilder.core.schema.Table;
import de.jaggl.sqlbuilder.core.testsupport.QueryExecutorTestSupport;

class UpdatebleQueryTest extends QueryExecutorTestSupport
{
    private static final Table TABLE = Table.create("table");

    private static final VarCharColumn LASTNAME = TABLE.varCharColumn("lastname").build();

    @Test
    void testQuery()
    {
        var queryExecutor = new MyQueryExecutor();

        assertThat(update(TABLE).set(LASTNAME, "Schumacher").execute(queryExecutor)).isEqualTo(5);
    }

    @Test
    void testInsertQuery()
    {
        var queryExecutor = new MyQueryExecutor();

        assertThat(insertInto(TABLE).set(LASTNAME, "Schumacher").executeAndReturnKey(queryExecutor)).isEqualTo(3);
    }
}
