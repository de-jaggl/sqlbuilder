package de.jaggl.sqlbuilder.core.functions;

import static de.jaggl.sqlbuilder.core.functions.Function.count;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.core.testsupport.FunctionConditionTestSupport;

class NumberColumnFunctionTest extends FunctionConditionTestSupport
{
    @Test
    void testNumberColumnFunctionConditions()
    {
        assertCondition(() -> count().isEqualTo((Integer) null)).isEqualTo("COUNT(*) IS NULL");
        assertCondition(() -> count().eq((Integer) null)).isEqualTo("COUNT(*) IS NULL");
        assertCondition(() -> count().isEqualTo(Integer.valueOf(123))).isEqualTo("COUNT(*) = 123");
        assertCondition(() -> count().eq(Integer.valueOf(123))).isEqualTo("COUNT(*) = 123");
        assertCondition(() -> count().isEqualTo(123)).isEqualTo("COUNT(*) = 123");
        assertCondition(() -> count().eq(123)).isEqualTo("COUNT(*) = 123");
        assertCondition(() -> count().isEqualTo(123.123)).isEqualTo("COUNT(*) = 123.123");
        assertCondition(() -> count().eq(123.123)).isEqualTo("COUNT(*) = 123.123");
        assertCondition(() -> count().isNotEqualTo((Integer) null)).isEqualTo("COUNT(*) IS NOT NULL");
        assertCondition(() -> count().nEq((Integer) null)).isEqualTo("COUNT(*) IS NOT NULL");
        assertCondition(() -> count().isNotEqualTo(Integer.valueOf(123))).isEqualTo("COUNT(*) != 123");
        assertCondition(() -> count().nEq(Integer.valueOf(123))).isEqualTo("COUNT(*) != 123");
        assertCondition(() -> count().isNotEqualTo(123)).isEqualTo("COUNT(*) != 123");
        assertCondition(() -> count().nEq(123)).isEqualTo("COUNT(*) != 123");
        assertCondition(() -> count().isNotEqualTo(123.123)).isEqualTo("COUNT(*) != 123.123");
        assertCondition(() -> count().nEq(123.123)).isEqualTo("COUNT(*) != 123.123");
        assertCondition(() -> count().isGreaterThan(Integer.valueOf(123))).isEqualTo("COUNT(*) > 123");
        assertCondition(() -> count().gt(Integer.valueOf(123))).isEqualTo("COUNT(*) > 123");
        assertCondition(() -> count().isGreaterThan(123)).isEqualTo("COUNT(*) > 123");
        assertCondition(() -> count().gt(123)).isEqualTo("COUNT(*) > 123");
        assertCondition(() -> count().isGreaterThan(123.123)).isEqualTo("COUNT(*) > 123.123");
        assertCondition(() -> count().gt(123.123)).isEqualTo("COUNT(*) > 123.123");
        assertCondition(() -> count().isGreaterThan(getOtherColumn())).isEqualTo("COUNT(*) > `table`.`other`");
        assertCondition(() -> count().gt(getOtherColumn())).isEqualTo("COUNT(*) > `table`.`other`");
        assertCondition(() -> count().isGreaterThanOrEqualTo(Integer.valueOf(123))).isEqualTo("COUNT(*) >= 123");
        assertCondition(() -> count().gtEq(Integer.valueOf(123))).isEqualTo("COUNT(*) >= 123");
        assertCondition(() -> count().isGreaterThanOrEqualTo(123)).isEqualTo("COUNT(*) >= 123");
        assertCondition(() -> count().gtEq(123)).isEqualTo("COUNT(*) >= 123");
        assertCondition(() -> count().isGreaterThanOrEqualTo(123.123)).isEqualTo("COUNT(*) >= 123.123");
        assertCondition(() -> count().gtEq(123.123)).isEqualTo("COUNT(*) >= 123.123");
        assertCondition(() -> count().isGreaterThanOrEqualTo(getOtherColumn())).isEqualTo("COUNT(*) >= `table`.`other`");
        assertCondition(() -> count().gtEq(getOtherColumn())).isEqualTo("COUNT(*) >= `table`.`other`");
        assertCondition(() -> count().isLessThan(Integer.valueOf(123))).isEqualTo("COUNT(*) < 123");
        assertCondition(() -> count().lt(Integer.valueOf(123))).isEqualTo("COUNT(*) < 123");
        assertCondition(() -> count().isLessThan(123)).isEqualTo("COUNT(*) < 123");
        assertCondition(() -> count().lt(123)).isEqualTo("COUNT(*) < 123");
        assertCondition(() -> count().isLessThan(123.123)).isEqualTo("COUNT(*) < 123.123");
        assertCondition(() -> count().lt(123.123)).isEqualTo("COUNT(*) < 123.123");
        assertCondition(() -> count().isLessThan(getOtherColumn())).isEqualTo("COUNT(*) < `table`.`other`");
        assertCondition(() -> count().lt(getOtherColumn())).isEqualTo("COUNT(*) < `table`.`other`");
        assertCondition(() -> count().isLessThanOrEqualTo(Integer.valueOf(123))).isEqualTo("COUNT(*) <= 123");
        assertCondition(() -> count().ltEq(Integer.valueOf(123))).isEqualTo("COUNT(*) <= 123");
        assertCondition(() -> count().isLessThanOrEqualTo(123)).isEqualTo("COUNT(*) <= 123");
        assertCondition(() -> count().ltEq(123)).isEqualTo("COUNT(*) <= 123");
        assertCondition(() -> count().isLessThanOrEqualTo(123.123)).isEqualTo("COUNT(*) <= 123.123");
        assertCondition(() -> count().ltEq(123.123)).isEqualTo("COUNT(*) <= 123.123");
        assertCondition(() -> count().isLessThanOrEqualTo(getOtherColumn())).isEqualTo("COUNT(*) <= `table`.`other`");
        assertCondition(() -> count().ltEq(getOtherColumn())).isEqualTo("COUNT(*) <= `table`.`other`");
        assertCondition(() -> count().isBetween(Integer.valueOf(123), Integer.valueOf(456))).isEqualTo("COUNT(*) BETWEEN 123 AND 456");
        assertCondition(() -> count().isBetween(123, 456)).isEqualTo("COUNT(*) BETWEEN 123 AND 456");
        assertCondition(() -> count().isBetween(123.123, 456.456)).isEqualTo("COUNT(*) BETWEEN 123.123 AND 456.456");
        assertCondition(() -> count().isBetween(getOtherColumn(), getOtherColumn2())).isEqualTo("COUNT(*) BETWEEN `table`.`other` AND `table`.`other2`");
        assertCondition(() -> count().isBetween(getOtherColumn(), Integer.valueOf(123))).isEqualTo("COUNT(*) BETWEEN `table`.`other` AND 123");
        assertCondition(() -> count().isBetween(getOtherColumn(), 123)).isEqualTo("COUNT(*) BETWEEN `table`.`other` AND 123");
        assertCondition(() -> count().isBetween(getOtherColumn(), 123.123)).isEqualTo("COUNT(*) BETWEEN `table`.`other` AND 123.123");
        assertCondition(() -> count().isBetween(Integer.valueOf(123), getOtherColumn())).isEqualTo("COUNT(*) BETWEEN 123 AND `table`.`other`");
        assertCondition(() -> count().isBetween(123, getOtherColumn())).isEqualTo("COUNT(*) BETWEEN 123 AND `table`.`other`");
        assertCondition(() -> count().isBetween(123.123, getOtherColumn())).isEqualTo("COUNT(*) BETWEEN 123.123 AND `table`.`other`");
    }
}
