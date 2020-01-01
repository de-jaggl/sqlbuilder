package de.jaggl.sqlbuilder.functions;

import static de.jaggl.sqlbuilder.functions.Function.count;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.testsupport.FunctionConditionTestSupport;

class ColumnFunctionTest extends FunctionConditionTestSupport
{
    @Test
    void testColumnFunctionConditions()
    {
        assertCondition(() -> count().isEqualTo(getOtherColumn())).isEqualTo("COUNT(*) = `table`.`other`");
        assertCondition(() -> count().isNotEqualTo(getOtherColumn())).isEqualTo("COUNT(*) != `table`.`other`");
    }
}
