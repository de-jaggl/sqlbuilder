package de.jaggl.sqlbuilder.core.columns.number.integer;

import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.core.columns.number.integer.SmallIntColumn;
import de.jaggl.sqlbuilder.core.columns.number.integer.SmallIntColumnBuilder;
import de.jaggl.sqlbuilder.core.schema.Table;
import de.jaggl.sqlbuilder.core.testsupport.ColumnAliasTestSupport;
import de.jaggl.sqlbuilder.core.testsupport.Consumers;

class SmallIntColumnTest extends IntegerColumnTest<SmallIntColumn, SmallIntColumnBuilder>
        implements ColumnAliasTestSupport<SmallIntColumn, SmallIntColumnBuilder, Integer>
{
    @Override
    public SmallIntColumnBuilder getColumnBuilder(Table table, String name)
    {
        return new SmallIntColumnBuilder(table, name);
    }

    @Override
    public BiFunction<SmallIntColumn, String, SmallIntColumn> getAliasFunction()
    {
        return (column, alias) -> column.as(alias);
    }

    @Test
    void testDecimalColumnDefinition()
    {
        assertBuild(Consumers::noAction).isEqualTo("SMALLINT DEFAULT NULL");
        assertBuild(builder -> builder.notNull()).isEqualTo("SMALLINT NOT NULL");
        assertBuild(builder -> builder.defaultNull()).isEqualTo("SMALLINT DEFAULT NULL");
        assertBuild(builder -> builder.noDefault()).isEqualTo("SMALLINT");
        assertBuild(builder -> builder.noDefault().autoIncrement()).isEqualTo("SMALLINT AUTO_INCREMENT");
        assertBuild(builder -> builder.size(5).defaultNull()).isEqualTo("SMALLINT(5) DEFAULT NULL");
        assertBuild(builder -> builder.size(5).defaultValue(123)).isEqualTo("SMALLINT(5) DEFAULT 123");
        assertBuild(builder -> builder.size(Integer.valueOf(5)).defaultValue(Integer.valueOf(123))).isEqualTo("SMALLINT(5) DEFAULT 123");
        assertBuild(builder -> builder.size(5).notNull().defaultValue(123)).isEqualTo("SMALLINT(5) NOT NULL DEFAULT 123");
    }
}
