package de.jaggl.sqlbuilder.core.columns.number.integer;

import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.core.columns.number.integer.TinyIntColumn;
import de.jaggl.sqlbuilder.core.columns.number.integer.TinyIntColumnBuilder;
import de.jaggl.sqlbuilder.core.schema.Table;
import de.jaggl.sqlbuilder.core.testsupport.ColumnAliasTestSupport;
import de.jaggl.sqlbuilder.core.testsupport.Consumers;

class TinyIntColumnTest extends IntegerColumnTest<TinyIntColumn, TinyIntColumnBuilder>
        implements ColumnAliasTestSupport<TinyIntColumn, TinyIntColumnBuilder, Integer>
{
    @Override
    public TinyIntColumnBuilder getColumnBuilder(Table table, String name)
    {
        return new TinyIntColumnBuilder(table, name);
    }

    @Override
    public BiFunction<TinyIntColumn, String, TinyIntColumn> getAliasFunction()
    {
        return (column, alias) -> column.as(alias);
    }

    @Test
    void testDecimalColumnDefinition()
    {
        assertBuild(Consumers::noAction).isEqualTo("TINYINT DEFAULT NULL");
        assertBuild(builder -> builder.notNull()).isEqualTo("TINYINT NOT NULL");
        assertBuild(builder -> builder.defaultNull()).isEqualTo("TINYINT DEFAULT NULL");
        assertBuild(builder -> builder.noDefault()).isEqualTo("TINYINT");
        assertBuild(builder -> builder.noDefault().autoIncrement()).isEqualTo("TINYINT AUTO_INCREMENT");
        assertBuild(builder -> builder.size(5).defaultNull()).isEqualTo("TINYINT(5) DEFAULT NULL");
        assertBuild(builder -> builder.size(5).defaultValue(123)).isEqualTo("TINYINT(5) DEFAULT 123");
        assertBuild(builder -> builder.size(Integer.valueOf(5)).defaultValue(Integer.valueOf(123))).isEqualTo("TINYINT(5) DEFAULT 123");
        assertBuild(builder -> builder.size(5).notNull().defaultValue(123)).isEqualTo("TINYINT(5) NOT NULL DEFAULT 123");
    }
}
