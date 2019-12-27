package com.avides.sqlbuilder.testsupport;

import static com.avides.sqlbuilder.dialect.Dialects.MYSQL;
import static com.avides.sqlbuilder.utils.Indentation.disabled;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Consumer;

import org.assertj.core.api.AbstractStringAssert;

import com.avides.sqlbuilder.columns.ColumnBuilder;
import com.avides.sqlbuilder.domain.BuildingContext;
import com.avides.sqlbuilder.schema.Table;

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
