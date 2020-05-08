package de.jaggl.sqlbuilder.core.columns;

import static de.jaggl.sqlbuilder.core.conditions.GenericCondition.GenericConditionType.IS_BETWEEN;
import static de.jaggl.sqlbuilder.core.domain.Placeholder.placeholder;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.core.columns.Column;
import de.jaggl.sqlbuilder.core.columns.ColumnBuilder;
import de.jaggl.sqlbuilder.core.columns.string.VarCharColumnBuilder;
import de.jaggl.sqlbuilder.core.conditions.GenericCondition;
import de.jaggl.sqlbuilder.core.schema.Table;
import de.jaggl.sqlbuilder.core.testsupport.ColumnTestSupport;

public abstract class ColumnTest<C extends Column, B extends ColumnBuilder<C, B, V>, V> extends ColumnTestSupport<C, B, V>
{
    @SuppressWarnings("unchecked")
    @Override
    protected B getColumnBuilder(Table table, String name)
    {
        return (B) new VarCharColumnBuilder(table, name);
    }

    @Test
    void testColumnConditions()
    {
        assertCondition(column -> column.isEqualTo(getOtherColumn())).isEqualTo("= `table`.`other`");
        assertCondition(column -> column.eq(getOtherColumn())).isEqualTo("= `table`.`other`");
        assertCondition(column -> column.isNotEqualTo(getOtherColumn())).isEqualTo("!= `table`.`other`");
        assertCondition(column -> column.nEq(getOtherColumn())).isEqualTo("!= `table`.`other`");
        assertCondition(column -> column.isNull()).isEqualTo("IS NULL");
        assertCondition(column -> column.isNotNull()).isEqualTo("IS NOT NULL");
    }

    @Test
    void testResolvePlaceholdersWithOwnCondition()
    {
        assertCondition(column -> new MyCondition(IS_BETWEEN, placeholder("anyColumnOfSpecialSize"), "hello", "world"))
                .isEqualTo("BETWEEN 'hello' AND 'world'");
    }

    private class MyCondition extends GenericCondition
    {
        public MyCondition(GenericConditionType type, Object... values)
        {
            super(type, values);
        }
    }
}
