package de.jaggl.sqlbuilder.testsupport;

import static de.jaggl.sqlbuilder.dialect.Dialects.MYSQL;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Consumer;
import java.util.function.Function;

import org.assertj.core.api.AbstractStringAssert;

import de.jaggl.sqlbuilder.columns.Column;
import de.jaggl.sqlbuilder.columns.ColumnBuilder;
import de.jaggl.sqlbuilder.conditions.Condition;
import de.jaggl.sqlbuilder.dialect.DialectBuilderTestSupport;
import de.jaggl.sqlbuilder.dialect.MySqlDialect;
import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.queries.Queries;
import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.utils.Indentation;

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
