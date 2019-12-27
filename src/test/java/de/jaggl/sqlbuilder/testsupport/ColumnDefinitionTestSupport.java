package de.jaggl.sqlbuilder.testsupport;

import static de.jaggl.sqlbuilder.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.utils.Indentation.disabled;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Consumer;

import org.assertj.core.api.AbstractStringAssert;

import de.jaggl.sqlbuilder.columns.ColumnBuilder;
import de.jaggl.sqlbuilder.domain.BuildingContext;
import de.jaggl.sqlbuilder.schema.Table;

public abstract class ColumnDefinitionTestSupport<T extends ColumnBuilder<?>>
{
    protected abstract T getColumnBuilder(Table table, String name);

    protected AbstractStringAssert<?> assertBuild(Consumer<T> consumer)
    {
        var context = new BuildingContext(MYSQL, "");
        var columnBuilder = getColumnBuilder(Table.create("anyTable"), "anyColumnName");
        consumer.accept(columnBuilder);
        return assertThat(columnBuilder.build().getColumnDefinition().getDefinition(context, disabled()));
    }
}
