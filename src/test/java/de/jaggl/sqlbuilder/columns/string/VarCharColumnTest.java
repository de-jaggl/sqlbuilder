package de.jaggl.sqlbuilder.columns.string;

import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.testsupport.ColumnAliasTestSupport;
import de.jaggl.sqlbuilder.testsupport.Consumers;

class VarCharColumnTest extends StringColumnTest<VarCharColumn, VarCharColumnBuilder> implements ColumnAliasTestSupport<VarCharColumn, VarCharColumnBuilder>
{
    @Override
    public VarCharColumnBuilder getColumnBuilder(Table table, String name)
    {
        return new VarCharColumnBuilder(table, name);
    }

    @Override
    public BiFunction<VarCharColumn, String, VarCharColumn> getAliasFunction()
    {
        return (column, alias) -> column.as(alias);
    }

    @Test
    void testVarCharColumnDefinition()
    {
        assertBuild(Consumers::noAction).isEqualTo("VARCHAR DEFAULT NULL");
        assertBuild(builder -> builder.notNull()).isEqualTo("VARCHAR NOT NULL");
        assertBuild(builder -> builder.defaultNull()).isEqualTo("VARCHAR DEFAULT NULL");
        assertBuild(builder -> builder.noDefault()).isEqualTo("VARCHAR");
        assertBuild(builder -> builder.size(10).defaultNull()).isEqualTo("VARCHAR(10) DEFAULT NULL");
        assertBuild(builder -> builder.size(Integer.valueOf(10)).defaultValue("anyDefaultValue")).isEqualTo("VARCHAR(10) DEFAULT 'anyDefaultValue'");
        assertBuild(builder -> builder.size(10).notNull().defaultValue("anyDefaultValue")).isEqualTo("VARCHAR(10) NOT NULL DEFAULT 'anyDefaultValue'");
    }
}
