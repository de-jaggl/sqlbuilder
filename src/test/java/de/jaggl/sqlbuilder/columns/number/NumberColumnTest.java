package de.jaggl.sqlbuilder.columns.number;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.columns.ColumnBuilder;
import de.jaggl.sqlbuilder.columns.ColumnTest;
import de.jaggl.sqlbuilder.columns.number.doubletype.DoubleColumnBuilder;
import de.jaggl.sqlbuilder.schema.Table;

public class NumberColumnTest<C extends NumberColumn<C, T>, B extends ColumnBuilder<C, B, T>, T extends Number> extends ColumnTest<C, B, T>
{
    @SuppressWarnings("unchecked")
    @Override
    protected B getColumnBuilder(Table table, String name)
    {
        return (B) new DoubleColumnBuilder(table, name);
    }

    @Test
    void testNumberColumnConditions()
    {
        assertCondition(column -> column.isEqualTo(Long.valueOf(123))).isEqualTo("= 123");
        assertCondition(column -> column.eq(Long.valueOf(123))).isEqualTo("= 123");
        assertCondition(column -> column.isEqualTo((Number) null)).isEqualTo("IS NULL");
        assertCondition(column -> column.eq((Number) null)).isEqualTo("IS NULL");
        assertCondition(column -> column.isEqualTo(123)).isEqualTo("= 123");
        assertCondition(column -> column.eq(123)).isEqualTo("= 123");
        assertCondition(column -> column.isEqualTo(123.123)).isEqualTo("= 123.123");
        assertCondition(column -> column.eq(123.123)).isEqualTo("= 123.123");
        assertCondition(column -> column.isNotEqualTo(Long.valueOf(123))).isEqualTo("!= 123");
        assertCondition(column -> column.nEq(Long.valueOf(123))).isEqualTo("!= 123");
        assertCondition(column -> column.isNotEqualTo((Number) null)).isEqualTo("IS NOT NULL");
        assertCondition(column -> column.nEq((Number) null)).isEqualTo("IS NOT NULL");
        assertCondition(column -> column.isNotEqualTo(123)).isEqualTo("!= 123");
        assertCondition(column -> column.nEq(123)).isEqualTo("!= 123");
        assertCondition(column -> column.isNotEqualTo(123.123)).isEqualTo("!= 123.123");
        assertCondition(column -> column.nEq(123.123)).isEqualTo("!= 123.123");
        assertCondition(column -> column.isLessThan(123)).isEqualTo("< 123");
        assertCondition(column -> column.lt(123)).isEqualTo("< 123");
        assertCondition(column -> column.isLessThan(123.123)).isEqualTo("< 123.123");
        assertCondition(column -> column.lt(123.123)).isEqualTo("< 123.123");
        assertCondition(column -> column.isLessThan(Double.valueOf(123.123))).isEqualTo("< 123.123");
        assertCondition(column -> column.lt(Double.valueOf(123.123))).isEqualTo("< 123.123");
        assertCondition(column -> column.isLessThan(getOtherColumn())).isEqualTo("< `table`.`other`");
        assertCondition(column -> column.lt(getOtherColumn())).isEqualTo("< `table`.`other`");
        assertCondition(column -> column.isLessThanOrEqualTo(123)).isEqualTo("<= 123");
        assertCondition(column -> column.ltEq(123)).isEqualTo("<= 123");
        assertCondition(column -> column.isLessThanOrEqualTo(123.123)).isEqualTo("<= 123.123");
        assertCondition(column -> column.ltEq(123.123)).isEqualTo("<= 123.123");
        assertCondition(column -> column.isLessThanOrEqualTo(Double.valueOf(123.123))).isEqualTo("<= 123.123");
        assertCondition(column -> column.ltEq(Double.valueOf(123.123))).isEqualTo("<= 123.123");
        assertCondition(column -> column.isLessThanOrEqualTo(getOtherColumn())).isEqualTo("<= `table`.`other`");
        assertCondition(column -> column.ltEq(getOtherColumn())).isEqualTo("<= `table`.`other`");
        assertCondition(column -> column.isGreaterThan(123)).isEqualTo("> 123");
        assertCondition(column -> column.gt(123)).isEqualTo("> 123");
        assertCondition(column -> column.isGreaterThan(123.123)).isEqualTo("> 123.123");
        assertCondition(column -> column.gt(123.123)).isEqualTo("> 123.123");
        assertCondition(column -> column.isGreaterThan(Double.valueOf(123.123))).isEqualTo("> 123.123");
        assertCondition(column -> column.gt(Double.valueOf(123.123))).isEqualTo("> 123.123");
        assertCondition(column -> column.isGreaterThan(getOtherColumn())).isEqualTo("> `table`.`other`");
        assertCondition(column -> column.gt(getOtherColumn())).isEqualTo("> `table`.`other`");
        assertCondition(column -> column.isGreaterThanOrEqualTo(123)).isEqualTo(">= 123");
        assertCondition(column -> column.gtEq(123)).isEqualTo(">= 123");
        assertCondition(column -> column.isGreaterThanOrEqualTo(123.123)).isEqualTo(">= 123.123");
        assertCondition(column -> column.gtEq(123.123)).isEqualTo(">= 123.123");
        assertCondition(column -> column.isGreaterThanOrEqualTo(Double.valueOf(123.123))).isEqualTo(">= 123.123");
        assertCondition(column -> column.gtEq(Double.valueOf(123.123))).isEqualTo(">= 123.123");
        assertCondition(column -> column.isGreaterThanOrEqualTo(getOtherColumn())).isEqualTo(">= `table`.`other`");
        assertCondition(column -> column.gtEq(getOtherColumn())).isEqualTo(">= `table`.`other`");
        assertCondition(column -> column.isBetween(123, 456)).isEqualTo("BETWEEN 123 AND 456");
        assertCondition(column -> column.isBetween(123.123, 456.456)).isEqualTo("BETWEEN 123.123 AND 456.456");
        assertCondition(column -> column.isBetween(Double.valueOf(123.123), Double.valueOf(456.456))).isEqualTo("BETWEEN 123.123 AND 456.456");
        assertCondition(column -> column.isBetween(123, getOtherColumn())).isEqualTo("BETWEEN 123 AND `table`.`other`");
        assertCondition(column -> column.isBetween(123.123, getOtherColumn())).isEqualTo("BETWEEN 123.123 AND `table`.`other`");
        assertCondition(column -> column.isBetween(Double.valueOf(123.123), getOtherColumn())).isEqualTo("BETWEEN 123.123 AND `table`.`other`");
        assertCondition(column -> column.isBetween(getOtherColumn(), 123)).isEqualTo("BETWEEN `table`.`other` AND 123");
        assertCondition(column -> column.isBetween(getOtherColumn(), 123.123)).isEqualTo("BETWEEN `table`.`other` AND 123.123");
        assertCondition(column -> column.isBetween(getOtherColumn(), Double.valueOf(123.123))).isEqualTo("BETWEEN `table`.`other` AND 123.123");
        assertCondition(column -> column.isBetween(getOtherColumn(), getOtherColumn2())).isEqualTo("BETWEEN `table`.`other` AND `table`.`other2`");
    }
}
