package de.jaggl.sqlbuilder.queries;

import static de.jaggl.sqlbuilder.queries.Queries.select;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.queryexecutor.SelectQueryExecutor;
import de.jaggl.sqlbuilder.schema.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

public class QueryableQueryTest
{
    private static final Table TABLE = Table.create("table");
    private SelectQueryExecutor<MyClass> selectQueryExecutor = new MySelectQueryExecutor();

    @Test
    void testQuery()
    {
        assertThat(select().from(TABLE).query(selectQueryExecutor))
                .containsExactly(new MyClass("anyValue1"), new MyClass("anyValue2"), new MyClass("anyValue3"));
        assertThat(select().from(TABLE).queryOne(selectQueryExecutor)).contains(new MyClass("anyValue"));
        assertThat(select().from(TABLE).queryExactOne(selectQueryExecutor)).isEqualTo(new MyClass("anyOtherValue"));
    }

    private class MySelectQueryExecutor implements SelectQueryExecutor<MyClass>
    {
        @Override
        public List<MyClass> query(Query select)
        {
            return new ArrayList<>(List.of(new MyClass("anyValue1"), new MyClass("anyValue2"), new MyClass("anyValue3")));
        }

        @Override
        public Optional<MyClass> queryOne(Query select)
        {
            return Optional.of(new MyClass("anyValue"));
        }

        @Override
        public MyClass queryExactOne(Query query)
        {
            return new MyClass("anyOtherValue");
        }
    }

    @AllArgsConstructor
    @EqualsAndHashCode
    private class MyClass
    {
        private String value;
    }
}
