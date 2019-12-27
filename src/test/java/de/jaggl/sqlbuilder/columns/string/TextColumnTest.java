package de.jaggl.sqlbuilder.columns.string;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.columns.string.TextColumnBuilder;
import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.testsupport.ColumnDefinitionTestSupport;
import de.jaggl.sqlbuilder.testsupport.Consumers;

class TextColumnTest extends ColumnDefinitionTestSupport<TextColumnBuilder>
{
    @Override
    protected TextColumnBuilder getColumnBuilder(Table table, String name)
    {
        return new TextColumnBuilder(table, name);
    }

    @Test
    void testGetDefinition()
    {
        assertBuild(Consumers::noAction).isEqualTo("TEXT DEFAULT NULL");
        assertBuild(builder -> builder.notNull()).isEqualTo("TEXT NOT NULL");
        assertBuild(builder -> builder.defaultNull()).isEqualTo("TEXT DEFAULT NULL");
        assertBuild(builder -> builder.noDefault()).isEqualTo("TEXT");
        assertBuild(builder -> builder.defaultValue("anyDefaultValue")).isEqualTo("TEXT DEFAULT 'anyDefaultValue'");
        assertBuild(builder -> builder.notNull().defaultValue("anyDefaultValue")).isEqualTo("TEXT NOT NULL DEFAULT 'anyDefaultValue'");
    }
}
