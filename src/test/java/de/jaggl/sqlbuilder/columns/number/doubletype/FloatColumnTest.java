package de.jaggl.sqlbuilder.columns.number.doubletype;

import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.testsupport.ColumnAliasTestSupport;
import de.jaggl.sqlbuilder.testsupport.Consumers;

class FloatColumnTest extends DoubleTypeColumnTest<FloatColumn, FloatColumnBuilder> implements ColumnAliasTestSupport<FloatColumn, FloatColumnBuilder, Double>
{
    @Override
    public FloatColumnBuilder getColumnBuilder(Table table, String name)
    {
        return new FloatColumnBuilder(table, name);
    }

    @Override
    public BiFunction<FloatColumn, String, FloatColumn> getAliasFunction()
    {
        return (column, alias) -> column.as(alias);
    }

    @Test
    void testDecimalColumnDefinition()
    {
        assertBuild(Consumers::noAction).isEqualTo("FLOAT DEFAULT NULL");
        assertBuild(builder -> builder.notNull()).isEqualTo("FLOAT NOT NULL");
        assertBuild(builder -> builder.defaultNull()).isEqualTo("FLOAT DEFAULT NULL");
        assertBuild(builder -> builder.noDefault()).isEqualTo("FLOAT");
        assertBuild(builder -> builder.size(3.3).defaultNull()).isEqualTo("FLOAT(3,3) DEFAULT NULL");
        assertBuild(builder -> builder.size(3.3).defaultValue(123.123)).isEqualTo("FLOAT(3,3) DEFAULT 123.123");
        assertBuild(builder -> builder.size(Double.valueOf(3.3)).defaultValue(Double.valueOf(123.123))).isEqualTo("FLOAT(3,3) DEFAULT 123.123");
        assertBuild(builder -> builder.size(3.3).notNull().defaultValue(123.123)).isEqualTo("FLOAT(3,3) NOT NULL DEFAULT 123.123");
    }
}
