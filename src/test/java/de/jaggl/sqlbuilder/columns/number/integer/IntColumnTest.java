package de.jaggl.sqlbuilder.columns.number.integer;

import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.testsupport.ColumnAliasTestSupport;
import de.jaggl.sqlbuilder.testsupport.Consumers;

class IntColumnTest extends IntegerColumnTest<IntColumn, IntColumnBuilder> implements ColumnAliasTestSupport<IntColumn, IntColumnBuilder>
{
    @Override
    public IntColumnBuilder getColumnBuilder(Table table, String name)
    {
        return new IntColumnBuilder(table, name);
    }

    @Override
    public BiFunction<IntColumn, String, IntColumn> getAliasFunction()
    {
        return (column, alias) -> column.as(alias);
    }

    @Test
    void testDecimalColumnDefinition()
    {
        assertBuild(Consumers::noAction).isEqualTo("INT DEFAULT NULL");
        assertBuild(builder -> builder.notNull()).isEqualTo("INT NOT NULL");
        assertBuild(builder -> builder.defaultNull()).isEqualTo("INT DEFAULT NULL");
        assertBuild(builder -> builder.noDefault()).isEqualTo("INT");
        assertBuild(builder -> builder.noDefault().autoIncrement()).isEqualTo("INT AUTO_INCREMENT");
        assertBuild(builder -> builder.size(5).defaultNull()).isEqualTo("INT(5) DEFAULT NULL");
        assertBuild(builder -> builder.size(5).defaultValue(123)).isEqualTo("INT(5) DEFAULT 123");
        assertBuild(builder -> builder.size(Integer.valueOf(5)).defaultValue(Integer.valueOf(123))).isEqualTo("INT(5) DEFAULT 123");
        assertBuild(builder -> builder.size(5).notNull().defaultValue(123)).isEqualTo("INT(5) NOT NULL DEFAULT 123");
    }
}
