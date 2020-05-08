package de.jaggl.sqlbuilder.core.testsupport;

import static de.jaggl.sqlbuilder.core.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.core.utils.Indentation.disabled;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.core.columns.Column;
import de.jaggl.sqlbuilder.core.columns.ColumnBuilder;
import de.jaggl.sqlbuilder.core.domain.BuildingContext;
import de.jaggl.sqlbuilder.core.schema.Table;

public interface ColumnAliasTestSupport<C extends Column, B extends ColumnBuilder<C, B, V>, V>
{
    B getColumnBuilder(Table table, String name);

    BiFunction<C, String, C> getAliasFunction();

    @Test
    default void testAlias()
    {
        var table = Table.create("table");
        var context = new BuildingContext(MYSQL, disabled().getDelimiter());
        var column = getColumnBuilder(table, "anyColumnName").build();
        assertThat(column.getAlias()).isNull();
        assertThat(column.getFullName(context)).isEqualTo("`table`.`anyColumnName`");
        assertThat(column.getFullNameOrAlias(context)).isEqualTo("`table`.`anyColumnName`");
        column = getAliasFunction().apply(column, "anyAlias");
        assertThat(column.getAlias()).isEqualTo("anyAlias");
        assertThat(column.getFullNameOrAlias(context)).isEqualTo("`table`.`anyColumnName`");
    }
}
