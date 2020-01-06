package de.jaggl.sqlbuilder.queries;

import static de.jaggl.sqlbuilder.queries.Queries.update;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.columns.string.VarCharColumn;
import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.testsupport.QueryExecutorTestSupport;

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
}
