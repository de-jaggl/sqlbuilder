package de.jaggl.sqlbuilder.columns.number.doubletype;

import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.testsupport.ColumnAliasTestSupport;
import de.jaggl.sqlbuilder.testsupport.Consumers;

class DecimalColumnTest extends DoubleTypeColumnTest<DecimalColumn, DecimalColumnBuilder> implements ColumnAliasTestSupport<DecimalColumn, DecimalColumnBuilder>
{
    @Override
    public DecimalColumnBuilder getColumnBuilder(Table table, String name)
    {
        return new DecimalColumnBuilder(table, name);
    }

    @Override
    public BiFunction<DecimalColumn, String, DecimalColumn> getAliasFunction()
    {
        return (column, alias) -> column.as(alias);
    }

    @Test
    void testDecimalColumnDefinition()
    {
        assertBuild(Consumers::noAction).isEqualTo("DECIMAL DEFAULT NULL");
        assertBuild(builder -> builder.notNull()).isEqualTo("DECIMAL NOT NULL");
        assertBuild(builder -> builder.defaultNull()).isEqualTo("DECIMAL DEFAULT NULL");
        assertBuild(builder -> builder.noDefault()).isEqualTo("DECIMAL");
        assertBuild(builder -> builder.size(3.3).defaultNull()).isEqualTo("DECIMAL(3,3) DEFAULT NULL");
        assertBuild(builder -> builder.size(3.3).defaultValue(123.123)).isEqualTo("DECIMAL(3,3) DEFAULT 123.123");
        assertBuild(builder -> builder.size(Double.valueOf(3.3)).defaultValue(Double.valueOf(123.123))).isEqualTo("DECIMAL(3,3) DEFAULT 123.123");
        assertBuild(builder -> builder.size(3.3).notNull().defaultValue(123.123)).isEqualTo("DECIMAL(3,3) NOT NULL DEFAULT 123.123");
    }
}
