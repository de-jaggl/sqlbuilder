package de.jaggl.sqlbuilder.core.testsupport;

import static de.jaggl.sqlbuilder.core.dialect.Dialects.MYSQL;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Consumer;
import java.util.function.Function;

import org.assertj.core.api.AbstractStringAssert;

import de.jaggl.sqlbuilder.core.columns.Column;
import de.jaggl.sqlbuilder.core.columns.ColumnBuilder;
import de.jaggl.sqlbuilder.core.conditions.Condition;
import de.jaggl.sqlbuilder.core.dialect.DialectBuilderTestSupport;
import de.jaggl.sqlbuilder.core.dialect.MySqlDialect;
import de.jaggl.sqlbuilder.core.domain.BuildingContext;
import de.jaggl.sqlbuilder.core.queries.Queries;
import de.jaggl.sqlbuilder.core.schema.Table;
import de.jaggl.sqlbuilder.core.utils.Indentation;

public abstract class ColumnTestSupport<C extends Column, B extends ColumnBuilder<C, B, V>, V>
{
    protected abstract B getColumnBuilder(Table table, String name);

    private static final Table TABLE = Table.create("table");

    protected AbstractStringAssert<?> assertBuild(Consumer<B> consumer)
    {
        var columnBuilder = getColumnBuilder(TABLE, "anyColumnName");
        var indentation = Indentation.disabled();
        consumer.accept(columnBuilder);
        return assertThat(DialectBuilderTestSupport.buildColumnDefinition(MySqlDialect.getInstance(), columnBuilder.build()
                .getColumnDefinition(), new BuildingContext(MySqlDialect.getInstance(), indentation.getDelimiter()), indentation));
    }

    protected C getOtherColumn()
    {
        return getColumnBuilder(TABLE, "other").build();
    }

    protected C getOtherColumn2()
    {
        return getColumnBuilder(TABLE, "other2").build();
    }

    protected AbstractStringAssert<?> assertCondition(Function<C, Condition> conditionFunction)
    {
        var column = getColumnBuilder(TABLE, "anyColumnName").build();
        return assertThat(Queries.select().from(TABLE).where(conditionFunction.apply(column)).build(MYSQL).substring(52));
    }
}
