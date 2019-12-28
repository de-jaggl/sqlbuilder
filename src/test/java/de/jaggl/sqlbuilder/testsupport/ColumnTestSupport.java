package de.jaggl.sqlbuilder.testsupport;

import static de.jaggl.sqlbuilder.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.utils.Indentation.disabled;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.assertj.core.api.AbstractStringAssert;
import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.columns.Column;
import de.jaggl.sqlbuilder.columns.ColumnBuilder;
import de.jaggl.sqlbuilder.conditions.Condition;
import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.queries.Queries;
import de.jaggl.sqlbuilder.schema.Table;

public abstract class ColumnTestSupport<C extends Column, B extends ColumnBuilder<C>>
{
    protected abstract B getColumnBuilder(Table table, String name);

    private static final BuildingContext MYSQL_CONTEXT = new BuildingContext(MYSQL, "");
    private static final Table TABLE = Table.create("table");

    protected AbstractStringAssert<?> assertBuild(Consumer<B> consumer)
    {
        var columnBuilder = getColumnBuilder(TABLE, "anyColumnName");
        consumer.accept(columnBuilder);
        return assertThat(columnBuilder.build().getColumnDefinition().getDefinition(MYSQL_CONTEXT, disabled()));
    }

    protected abstract BiFunction<C, String, C> getAliasFunction();

    @Test
    void testAlias()
    {
        var column = getColumnBuilder(TABLE, "anyColumnName").build();
        assertThat(column.getAlias()).isNull();
        assertThat(column.getFullName(MYSQL_CONTEXT)).isEqualTo("`table`.`anyColumnName`");
        assertThat(column.getFullNameOrAlias(MYSQL_CONTEXT)).isEqualTo("`table`.`anyColumnName`");
        column = getAliasFunction().apply(column, "anyAlias");
        assertThat(column.getAlias()).isEqualTo("anyAlias");
        assertThat(column.getFullNameOrAlias(MYSQL_CONTEXT)).isEqualTo("`table`.`anyColumnName`");
    }

    protected C getOtherColumn()
    {
        return getColumnBuilder(TABLE, "other").build();
    }

    protected AbstractStringAssert<?> assertCondition(Function<C, Condition> conditionFunction)
    {
        var column = getColumnBuilder(TABLE, "anyColumnName").build();
        return assertThat(Queries.select().from(TABLE).where(conditionFunction.apply(column)).build(MYSQL).substring(52));
    }
}
