package de.jaggl.sqlbuilder.core.functions;

import static de.jaggl.sqlbuilder.core.functions.Function.avg;
import static de.jaggl.sqlbuilder.core.functions.Function.count;
import static de.jaggl.sqlbuilder.core.functions.Function.max;
import static de.jaggl.sqlbuilder.core.functions.Function.min;
import static de.jaggl.sqlbuilder.core.functions.Function.now;
import static de.jaggl.sqlbuilder.core.functions.Function.sum;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.core.testsupport.FunctionAssert;
import de.jaggl.sqlbuilder.core.testsupport.FunctionConditionTestSupport;

class FunctionTest extends FunctionConditionTestSupport implements FunctionAssert
{
    @Test
    void testFunctionDefinitions()
    {
        assertFunction(now()).isEqualTo("NOW()");
        assertFunction(count()).isEqualTo("COUNT(*)");
        assertFunction(count(getOtherColumn())).isEqualTo("COUNT(`table`.`other`)");
        assertFunction(avg(getOtherColumn())).isEqualTo("AVG(`table`.`other`)");
        assertFunction(max(getOtherColumn())).isEqualTo("MAX(`table`.`other`)");
        assertFunction(min(getOtherColumn())).isEqualTo("MIN(`table`.`other`)");
        assertFunction(sum(getOtherColumn())).isEqualTo("SUM(`table`.`other`)");
    }
}
