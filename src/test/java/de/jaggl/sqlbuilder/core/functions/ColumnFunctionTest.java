package de.jaggl.sqlbuilder.core.functions;

import static de.jaggl.sqlbuilder.core.functions.Function.count;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.core.testsupport.FunctionConditionTestSupport;

class ColumnFunctionTest extends FunctionConditionTestSupport
{
    @Test
    void testColumnFunctionConditions()
    {
        assertCondition(() -> count().isEqualTo(getOtherColumn())).isEqualTo("COUNT(*) = `table`.`other`");
        assertCondition(() -> count().eq(getOtherColumn())).isEqualTo("COUNT(*) = `table`.`other`");
        assertCondition(() -> count().isNotEqualTo(getOtherColumn())).isEqualTo("COUNT(*) != `table`.`other`");
        assertCondition(() -> count().nEq(getOtherColumn())).isEqualTo("COUNT(*) != `table`.`other`");
    }
}
