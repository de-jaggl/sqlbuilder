package de.jaggl.sqlbuilder.columns;

import static de.jaggl.sqlbuilder.conditions.GenericCondition.GenericConditionType.IS_BETWEEN;
import static de.jaggl.sqlbuilder.domain.Placeholder.placeholder;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.columns.string.VarCharColumnBuilder;
import de.jaggl.sqlbuilder.conditions.GenericCondition;
import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.testsupport.ColumnTestSupport;

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
