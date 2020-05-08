package de.jaggl.sqlbuilder.core.columns.string;

import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.core.columns.string.CharColumn;
import de.jaggl.sqlbuilder.core.columns.string.CharColumnBuilder;
import de.jaggl.sqlbuilder.core.schema.Table;
import de.jaggl.sqlbuilder.core.testsupport.ColumnAliasTestSupport;
import de.jaggl.sqlbuilder.core.testsupport.Consumers;

class CharColumnTest extends StringColumnTest<CharColumn, CharColumnBuilder> implements ColumnAliasTestSupport<CharColumn, CharColumnBuilder, CharSequence>
{
    @Override
    public CharColumnBuilder getColumnBuilder(Table table, String name)
    {
        return new CharColumnBuilder(table, name);
    }

    @Override
    public BiFunction<CharColumn, String, CharColumn> getAliasFunction()
    {
        return (column, alias) -> column.as(alias);
    }

    @Test
    void testCharColumnDefinition()
    {
        assertBuild(Consumers::noAction).isEqualTo("CHAR DEFAULT NULL");
        assertBuild(builder -> builder.notNull()).isEqualTo("CHAR NOT NULL");
        assertBuild(builder -> builder.defaultNull()).isEqualTo("CHAR DEFAULT NULL");
        assertBuild(builder -> builder.noDefault()).isEqualTo("CHAR");
        assertBuild(builder -> builder.size(10).defaultNull()).isEqualTo("CHAR(10) DEFAULT NULL");
        assertBuild(builder -> builder.size(Integer.valueOf(10)).defaultValue("anyDefaultValue")).isEqualTo("CHAR(10) DEFAULT 'anyDefaultValue'");
        assertBuild(builder -> builder.size(10).notNull().defaultValue("anyDefaultValue")).isEqualTo("CHAR(10) NOT NULL DEFAULT 'anyDefaultValue'");
    }
}
