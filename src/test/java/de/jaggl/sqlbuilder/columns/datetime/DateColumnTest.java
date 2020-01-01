package de.jaggl.sqlbuilder.columns.datetime;

import static de.jaggl.sqlbuilder.domain.LikeType.AFTER;
import static de.jaggl.sqlbuilder.domain.LikeType.BEFORE;
import static de.jaggl.sqlbuilder.domain.LikeType.BOTH;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.columns.ColumnTest;
import de.jaggl.sqlbuilder.schema.Table;
import de.jaggl.sqlbuilder.testsupport.ColumnAliasTestSupport;
import de.jaggl.sqlbuilder.testsupport.Consumers;

class DateColumnTest extends ColumnTest<DateColumn, DateColumnBuilder> implements ColumnAliasTestSupport<DateColumn, DateColumnBuilder>
{
    @Override
    public DateColumnBuilder getColumnBuilder(Table table, String name)
    {
        return new DateColumnBuilder(table, name);
    }

    @Override
    public BiFunction<DateColumn, String, DateColumn> getAliasFunction()
    {
        return (column, alias) -> column.as(alias);
    }

    @Test
    void testDefinition()
    {
        assertBuild(Consumers::noAction).isEqualTo("DATE DEFAULT NULL");
        assertBuild(builder -> builder.notNull()).isEqualTo("DATE NOT NULL");
        assertBuild(builder -> builder.defaultNull()).isEqualTo("DATE DEFAULT NULL");
        assertBuild(builder -> builder.noDefault()).isEqualTo("DATE");
        assertBuild(builder -> builder.defaultValue(LocalDate.of(1981, 12, 29))).isEqualTo("DATE DEFAULT '1981-12-29'");
        assertBuild(builder -> builder.notNull().defaultValue(LocalDate.of(1981, 12, 29))).isEqualTo("DATE NOT NULL DEFAULT '1981-12-29'");
    }

    @Test
    void testDateColumnConditions()
    {
        assertCondition(column -> column.isEqualTo(LocalDate.of(1981, 12, 29))).isEqualTo("= '1981-12-29'");
        assertCondition(column -> column.isEqualTo(toDate(LocalDate.of(1981, 12, 29)))).isEqualTo("= '1981-12-29'");
        assertCondition(column -> column.isEqualTo((LocalDate) null)).isEqualTo("IS NULL");
        assertCondition(column -> column.isEqualTo((Date) null)).isEqualTo("IS NULL");
        assertCondition(column -> column.isNotEqualTo(LocalDate.of(1981, 12, 29))).isEqualTo("!= '1981-12-29'");
        assertCondition(column -> column.isNotEqualTo(toDate(LocalDate.of(1981, 12, 29)))).isEqualTo("!= '1981-12-29'");
        assertCondition(column -> column.isNotEqualTo((LocalDate) null)).isEqualTo("IS NOT NULL");
        assertCondition(column -> column.isNotEqualTo((Date) null)).isEqualTo("IS NOT NULL");
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
        assertCondition(column -> column.isAfter(LocalDate.of(1981, 12, 29))).isEqualTo("> '1981-12-29'");
        assertCondition(column -> column.isAfter(toDate(LocalDate.of(1981, 12, 29)))).isEqualTo("> '1981-12-29'");
        assertCondition(column -> column.isAfter(getOtherColumn())).isEqualTo("> `table`.`other`");
        assertCondition(column -> column.isAfterOrEqualTo(LocalDate.of(1981, 12, 29))).isEqualTo(">= '1981-12-29'");
        assertCondition(column -> column.isAfterOrEqualTo(toDate(LocalDate.of(1981, 12, 29)))).isEqualTo(">= '1981-12-29'");
        assertCondition(column -> column.isAfterOrEqualTo(getOtherColumn())).isEqualTo(">= `table`.`other`");
        assertCondition(column -> column.isBefore(LocalDate.of(1981, 12, 29))).isEqualTo("< '1981-12-29'");
        assertCondition(column -> column.isBefore(toDate(LocalDate.of(1981, 12, 29)))).isEqualTo("< '1981-12-29'");
        assertCondition(column -> column.isBefore(getOtherColumn())).isEqualTo("< `table`.`other`");
        assertCondition(column -> column.isBeforeOrEqualTo(LocalDate.of(1981, 12, 29))).isEqualTo("<= '1981-12-29'");
        assertCondition(column -> column.isBeforeOrEqualTo(toDate(LocalDate.of(1981, 12, 29)))).isEqualTo("<= '1981-12-29'");
        assertCondition(column -> column.isBeforeOrEqualTo(getOtherColumn())).isEqualTo("<= `table`.`other`");
        assertCondition(column -> column.isBetween(LocalDate.of(1981, 12, 29), getOtherColumn())).isEqualTo("BETWEEN '1981-12-29' AND `table`.`other`");
        assertCondition(column -> column.isBetween(toDate(LocalDate.of(1981, 12, 29)), getOtherColumn())).isEqualTo("BETWEEN '1981-12-29' AND `table`.`other`");
        assertCondition(column -> column.isBetween(getOtherColumn(), getOtherColumn2())).isEqualTo("BETWEEN `table`.`other` AND `table`.`other2`");
        assertCondition(column -> column.isBetween(getOtherColumn(), LocalDate.of(2019, 12, 28))).isEqualTo("BETWEEN `table`.`other` AND '2019-12-28'");
        assertCondition(column -> column.isBetween(getOtherColumn(), toDate(LocalDate.of(2019, 12, 28)))).isEqualTo("BETWEEN `table`.`other` AND '2019-12-28'");
        assertCondition(column -> column.isBetween(LocalDate.of(1981, 12, 29), LocalDate.of(2019, 12, 28))).isEqualTo("BETWEEN '1981-12-29' AND '2019-12-28'");
        assertCondition(column -> column.isBetween(toDate(LocalDate.of(1981, 12, 29)), LocalDate.of(2019, 12, 28)))
                .isEqualTo("BETWEEN '1981-12-29' AND '2019-12-28'");
        assertCondition(column -> column.isBetween(LocalDate.of(1981, 12, 29), toDate(LocalDate.of(2019, 12, 28))))
                .isEqualTo("BETWEEN '1981-12-29' AND '2019-12-28'");
        assertCondition(column -> column.isBetween(toDate(LocalDate.of(1981, 12, 29)), toDate(LocalDate.of(2019, 12, 28))))
                .isEqualTo("BETWEEN '1981-12-29' AND '2019-12-28'");
    }

    private static final Date toDate(LocalDate localDate)
    {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
