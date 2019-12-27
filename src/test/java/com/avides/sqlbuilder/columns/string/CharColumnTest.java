package com.avides.sqlbuilder.columns.string;

import org.junit.jupiter.api.Test;

import com.avides.sqlbuilder.schema.Table;
import com.avides.sqlbuilder.testsupport.ColumnDefinitionTestSupport;
import com.avides.sqlbuilder.testsupport.Consumers;

class CharColumnTest extends ColumnDefinitionTestSupport<CharColumnBuilder>
{
    @Override
    protected CharColumnBuilder getColumnBuilder(Table table, String name)
    {
        return new CharColumnBuilder(table, name);
    }

    @Test
    void testDefinition()
    {
        assertBuild(Consumers::noAction).isEqualTo("CHAR DEFAULT NULL");
        assertBuild(builder -> builder.notNull()).isEqualTo("CHAR NOT NULL");
        assertBuild(builder -> builder.defaultNull()).isEqualTo("CHAR DEFAULT NULL");
        assertBuild(builder -> builder.noDefault()).isEqualTo("CHAR");
        assertBuild(builder -> builder.size(10).defaultNull()).isEqualTo("CHAR(10) DEFAULT NULL");
        assertBuild(builder -> builder.size(10).defaultValue("anyDefaultValue")).isEqualTo("CHAR(10) DEFAULT 'anyDefaultValue'");
        assertBuild(builder -> builder.size(10).notNull().defaultValue("anyDefaultValue")).isEqualTo("CHAR(10) NOT NULL DEFAULT 'anyDefaultValue'");
    }
}
