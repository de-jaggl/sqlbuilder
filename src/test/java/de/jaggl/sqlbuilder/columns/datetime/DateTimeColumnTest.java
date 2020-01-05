package de.jaggl.sqlbuilder.columns.datetime;

import static de.jaggl.sqlbuilder.domain.LikeType.AFTER;
import static de.jaggl.sqlbuilder.domain.LikeType.BEFORE;
import static de.jaggl.sqlbuilder.domain.LikeType.BOTH;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.columns.ColumnTest;
import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.testsupport.ColumnAliasTestSupport;
import de.jaggl.sqlbuilder.testsupport.Consumers;

class DateTimeColumnTest extends ColumnTest<DateTimeColumn, DateTimeColumnBuilder> implements ColumnAliasTestSupport<DateTimeColumn, DateTimeColumnBuilder>
{
    @Override
    public DateTimeColumnBuilder getColumnBuilder(Table table, String name)
    {
        return new DateTimeColumnBuilder(table, name);
    }

    @Override
    public BiFunction<DateTimeColumn, String, DateTimeColumn> getAliasFunction()
    {
        return (column, alias) -> column.as(alias);
    }

    @Test
    void testDefinition()
    {
        assertBuild(Consumers::noAction).isEqualTo("DATETIME DEFAULT NULL");
        assertBuild(builder -> builder.notNull()).isEqualTo("DATETIME NOT NULL");
        assertBuild(builder -> builder.defaultNull()).isEqualTo("DATETIME DEFAULT NULL");
        assertBuild(builder -> builder.noDefault()).isEqualTo("DATETIME");
        assertBuild(builder -> builder.defaultValue(LocalDateTime.of(1981, 12, 29, 21, 3, 51))).isEqualTo("DATETIME DEFAULT '1981-12-29 21:03:51.000000'");
        assertBuild(builder -> builder.notNull().defaultValue(LocalDateTime.of(1981, 12, 29, 21, 3, 51)))
                .isEqualTo("DATETIME NOT NULL DEFAULT '1981-12-29 21:03:51.000000'");
    }

    @Test
    void testDateTimeColumnConditions()
    {
        assertCondition(column -> column.isEqualTo(LocalDateTime.of(1981, 12, 29, 21, 3, 51))).isEqualTo("= '1981-12-29 21:03:51.000000'");
        assertCondition(column -> column.eq(LocalDateTime.of(1981, 12, 29, 21, 3, 51))).isEqualTo("= '1981-12-29 21:03:51.000000'");
        assertCondition(column -> column.isEqualTo(toDate(LocalDateTime.of(1981, 12, 29, 21, 3, 51)))).isEqualTo("= '1981-12-29 21:03:51.000000'");
        assertCondition(column -> column.eq(toDate(LocalDateTime.of(1981, 12, 29, 21, 3, 51)))).isEqualTo("= '1981-12-29 21:03:51.000000'");
        assertCondition(column -> column.isEqualTo((LocalDateTime) null)).isEqualTo("IS NULL");
        assertCondition(column -> column.eq((LocalDateTime) null)).isEqualTo("IS NULL");
        assertCondition(column -> column.isEqualTo((Date) null)).isEqualTo("IS NULL");
        assertCondition(column -> column.eq((Date) null)).isEqualTo("IS NULL");
        assertCondition(column -> column.isNotEqualTo(LocalDateTime.of(1981, 12, 29, 21, 3, 51))).isEqualTo("!= '1981-12-29 21:03:51.000000'");
        assertCondition(column -> column.nEq(LocalDateTime.of(1981, 12, 29, 21, 3, 51))).isEqualTo("!= '1981-12-29 21:03:51.000000'");
        assertCondition(column -> column.isNotEqualTo(toDate(LocalDateTime.of(1981, 12, 29, 21, 3, 51)))).isEqualTo("!= '1981-12-29 21:03:51.000000'");
        assertCondition(column -> column.nEq(toDate(LocalDateTime.of(1981, 12, 29, 21, 3, 51)))).isEqualTo("!= '1981-12-29 21:03:51.000000'");
        assertCondition(column -> column.isNotEqualTo((LocalDateTime) null)).isEqualTo("IS NOT NULL");
        assertCondition(column -> column.nEq((LocalDateTime) null)).isEqualTo("IS NOT NULL");
        assertCondition(column -> column.isNotEqualTo((Date) null)).isEqualTo("IS NOT NULL");
        assertCondition(column -> column.nEq((Date) null)).isEqualTo("IS NOT NULL");
        assertCondition(column -> column.isLike("1981-12-%")).isEqualTo("LIKE '1981-12-%'");
        assertCondition(column -> column.isLike((String) null)).isEqualTo("IS NULL");
        assertCondition(column -> column.isLike("-12-29", BEFORE)).isEqualTo("LIKE '%-12-29'");
        assertCondition(column -> column.isLike(null, BEFORE)).isEqualTo("IS NULL");
        assertCondition(column -> column.isLike("1981-12-", AFTER)).isEqualTo("LIKE '1981-12-%'");
        assertCondition(column -> column.isLike(null, AFTER)).isEqualTo("IS NULL");
        assertCondition(column -> column.isLike("-12-", BOTH)).isEqualTo("LIKE '%-12-%'");
        assertCondition(column -> column.isLike(null, BOTH)).isEqualTo("IS NULL");
        assertCondition(column -> column.isLike(getOtherColumn())).isEqualTo("LIKE `table`.`other`");
        assertCondition(column -> column.isNotLike("1981-12-%")).isEqualTo("NOT LIKE '1981-12-%'");
        assertCondition(column -> column.isNotLike((String) null)).isEqualTo("IS NOT NULL");
        assertCondition(column -> column.isNotLike("-12-29", BEFORE)).isEqualTo("NOT LIKE '%-12-29'");
        assertCondition(column -> column.isNotLike(null, BEFORE)).isEqualTo("IS NOT NULL");
        assertCondition(column -> column.isNotLike("1981-12-", AFTER)).isEqualTo("NOT LIKE '1981-12-%'");
        assertCondition(column -> column.isNotLike(null, AFTER)).isEqualTo("IS NOT NULL");
        assertCondition(column -> column.isNotLike("-12-", BOTH)).isEqualTo("NOT LIKE '%-12-%'");
        assertCondition(column -> column.isNotLike(null, BOTH)).isEqualTo("IS NOT NULL");
        assertCondition(column -> column.isNotLike(getOtherColumn())).isEqualTo("NOT LIKE `table`.`other`");
        assertCondition(column -> column.isAfter(LocalDateTime.of(1981, 12, 29, 21, 3, 51))).isEqualTo("> '1981-12-29 21:03:51.000000'");
        assertCondition(column -> column.isAfter(toDate(LocalDateTime.of(1981, 12, 29, 21, 3, 51)))).isEqualTo("> '1981-12-29 21:03:51.000000'");
        assertCondition(column -> column.isAfter(getOtherColumn())).isEqualTo("> `table`.`other`");
        assertCondition(column -> column.isAfterOrEqualTo(LocalDateTime.of(1981, 12, 29, 21, 3, 51))).isEqualTo(">= '1981-12-29 21:03:51.000000'");
        assertCondition(column -> column.isAfterOrEqualTo(toDate(LocalDateTime.of(1981, 12, 29, 21, 3, 51)))).isEqualTo(">= '1981-12-29 21:03:51.000000'");
        assertCondition(column -> column.isAfterOrEqualTo(getOtherColumn())).isEqualTo(">= `table`.`other`");
        assertCondition(column -> column.isBefore(LocalDateTime.of(1981, 12, 29, 21, 3, 51))).isEqualTo("< '1981-12-29 21:03:51.000000'");
        assertCondition(column -> column.isBefore(toDate(LocalDateTime.of(1981, 12, 29, 21, 3, 51)))).isEqualTo("< '1981-12-29 21:03:51.000000'");
        assertCondition(column -> column.isBefore(getOtherColumn())).isEqualTo("< `table`.`other`");
        assertCondition(column -> column.isBeforeOrEqualTo(LocalDateTime.of(1981, 12, 29, 21, 3, 51))).isEqualTo("<= '1981-12-29 21:03:51.000000'");
        assertCondition(column -> column.isBeforeOrEqualTo(toDate(LocalDateTime.of(1981, 12, 29, 21, 3, 51)))).isEqualTo("<= '1981-12-29 21:03:51.000000'");
        assertCondition(column -> column.isBeforeOrEqualTo(getOtherColumn())).isEqualTo("<= `table`.`other`");
        assertCondition(column -> column.isBetween(LocalDateTime.of(1981, 12, 29, 21, 3, 51), LocalDateTime.of(2019, 12, 28, 21, 3, 51)))
                .isEqualTo("BETWEEN '1981-12-29 21:03:51.000000' AND '2019-12-28 21:03:51.000000'");
        assertCondition(column -> column.isBetween(toDate(LocalDateTime.of(1981, 12, 29, 21, 3, 51)), LocalDateTime.of(2019, 12, 28, 21, 3, 51)))
                .isEqualTo("BETWEEN '1981-12-29 21:03:51.000000' AND '2019-12-28 21:03:51.000000'");
        assertCondition(column -> column.isBetween(LocalDateTime.of(1981, 12, 29, 21, 3, 51), toDate(LocalDateTime.of(2019, 12, 28, 21, 3, 51))))
                .isEqualTo("BETWEEN '1981-12-29 21:03:51.000000' AND '2019-12-28 21:03:51.000000'");
        assertCondition(column -> column.isBetween(toDate(LocalDateTime.of(1981, 12, 29, 21, 3, 51)), toDate(LocalDateTime.of(2019, 12, 28, 21, 3, 51))))
                .isEqualTo("BETWEEN '1981-12-29 21:03:51.000000' AND '2019-12-28 21:03:51.000000'");
        assertCondition(column -> column.isBetween(LocalDateTime.of(1981, 12, 29, 21, 3, 51), getOtherColumn()))
                .isEqualTo("BETWEEN '1981-12-29 21:03:51.000000' AND `table`.`other`");
        assertCondition(column -> column.isBetween(toDate(LocalDateTime.of(1981, 12, 29, 21, 3, 51)), getOtherColumn()))
                .isEqualTo("BETWEEN '1981-12-29 21:03:51.000000' AND `table`.`other`");
        assertCondition(column -> column.isBetween(getOtherColumn(), LocalDateTime.of(2019, 12, 28, 21, 3, 51)))
                .isEqualTo("BETWEEN `table`.`other` AND '2019-12-28 21:03:51.000000'");
        assertCondition(column -> column.isBetween(getOtherColumn(), toDate(LocalDateTime.of(2019, 12, 28, 21, 3, 51))))
                .isEqualTo("BETWEEN `table`.`other` AND '2019-12-28 21:03:51.000000'");
        assertCondition(column -> column.isBetween(getOtherColumn(), getOtherColumn2())).isEqualTo("BETWEEN `table`.`other` AND `table`.`other2`");
    }

    private static Date toDate(LocalDateTime localDateTime)
    {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
