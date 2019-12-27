package com.avides.sqlbuilder.columns.string;

import org.junit.jupiter.api.Test;

import com.avides.sqlbuilder.schema.Table;
import com.avides.sqlbuilder.testsupport.ColumnDefinitionTestSupport;
import com.avides.sqlbuilder.testsupport.Consumers;

class VarCharColumnTest extends ColumnDefinitionTestSupport<VarCharColumnBuilder>
{
    @Override
    protected VarCharColumnBuilder getColumnBuilder(Table table, String name)
    {
        return new VarCharColumnBuilder(table, name);
    }

    @Test
    void testDefinition()
    {
        assertBuild(Consumers::noAction).isEqualTo("VARCHAR DEFAULT NULL");
        assertBuild(builder -> builder.notNull()).isEqualTo("VARCHAR NOT NULL");
        assertBuild(builder -> builder.defaultNull()).isEqualTo("VARCHAR DEFAULT NULL");
        assertBuild(builder -> builder.noDefault()).isEqualTo("VARCHAR");
        assertBuild(builder -> builder.size(10).defaultNull()).isEqualTo("VARCHAR(10) DEFAULT NULL");
        assertBuild(builder -> builder.size(10).defaultValue("anyDefaultValue")).isEqualTo("VARCHAR(10) DEFAULT 'anyDefaultValue'");
        assertBuild(builder -> builder.size(10).notNull().defaultValue("anyDefaultValue")).isEqualTo("VARCHAR(10) NOT NULL DEFAULT 'anyDefaultValue'");
    }
}
