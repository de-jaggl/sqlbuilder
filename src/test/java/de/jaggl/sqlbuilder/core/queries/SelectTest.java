package de.jaggl.sqlbuilder.core.queries;

import static de.jaggl.sqlbuilder.core.conditions.Condition.emptyCondition;
import static de.jaggl.sqlbuilder.core.conditions.Condition.nested;
import static de.jaggl.sqlbuilder.core.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.core.dialect.Dialects.SYBASE;
import static de.jaggl.sqlbuilder.core.domain.LikeType.AFTER;
import static de.jaggl.sqlbuilder.core.functions.Function.count;
import static de.jaggl.sqlbuilder.core.functions.Function.min;
import static de.jaggl.sqlbuilder.core.functions.Function.sum;
import static de.jaggl.sqlbuilder.core.queries.Queries.select;
import static de.jaggl.sqlbuilder.core.queries.Queries.selectDistinct;
import static de.jaggl.sqlbuilder.core.queries.Select.clearGroupBys;
import static de.jaggl.sqlbuilder.core.queries.Select.clearHavings;
import static de.jaggl.sqlbuilder.core.queries.Select.clearJoins;
import static de.jaggl.sqlbuilder.core.queries.Select.clearLimit;
import static de.jaggl.sqlbuilder.core.queries.Select.clearOrdering;
import static de.jaggl.sqlbuilder.core.queries.Select.clearSelects;
import static de.jaggl.sqlbuilder.core.queries.Select.clearWheres;
import static de.jaggl.sqlbuilder.core.utils.Indentation.enabled;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.core.columns.datetime.DateColumn;
import de.jaggl.sqlbuilder.core.columns.datetime.DateTimeColumn;
import de.jaggl.sqlbuilder.core.columns.number.doubletype.DoubleColumn;
import de.jaggl.sqlbuilder.core.columns.number.integer.IntColumn;
import de.jaggl.sqlbuilder.core.columns.string.VarCharColumn;
import de.jaggl.sqlbuilder.core.conditions.Condition;
import de.jaggl.sqlbuilder.core.domain.Queryable;
import de.jaggl.sqlbuilder.core.domain.Selectable;
import de.jaggl.sqlbuilder.core.schema.Schema;
import de.jaggl.sqlbuilder.core.schema.Table;

class SelectTest
{
    public static final Schema DBA = Schema.create("dba");

    public static final Table PERSONS = DBA.table("persons");

    public static final Table GROUPS = DBA.table("groups");

    public static final VarCharColumn NAME = GROUPS.varCharColumn("name").build();

    public static final VarCharColumn FORENAME = PERSONS.varCharColumn("forename").size(50).nullable().build();
    public static final VarCharColumn LASTNAME = PERSONS.varCharColumn("lastname").size(50).defaultNull().build();
    public static final VarCharColumn NICKNAME = PERSONS.varCharColumn("nickname").size(30).nullable().defaultValue("Schubi").build();
    public static final IntColumn AGE = PERSONS.intColumn("age").size(5).unsigned().autoIncrement().build();
    public static final DoubleColumn SIZE = PERSONS.doubleColumn("size").size(2.2).unsigned().defaultValue(55.8).build();
    public static final DateColumn BIRTHDAY = PERSONS.dateColumn("birthday").build();
    public static final DateTimeColumn HAPPENING = PERSONS.dateTimeColumn("happening").build();

    @Test
    void testBuildSimpleSelect()
    {
        assertThat(select().from(PERSONS).whereNot(LASTNAME.isEqualTo(NICKNAME).and(FORENAME.isNull()).and(NICKNAME.isNotIn("a", "b", "c"))).build(MYSQL))
                .isEqualTo("SELECT * FROM `dba`.`persons` WHERE NOT (`dba`.`persons`.`lastname` = `dba`.`persons`.`nickname` AND `dba`.`persons`.`forename` IS NULL AND `dba`.`persons`.`nickname` NOT IN ('a', 'b', 'c'))");
        assertThat(select().from(Queryable.plain("persons").as("p"))
                .whereNot(LASTNAME.isEqualTo(NICKNAME).and(FORENAME.isNull()).and(NICKNAME.isNotIn("a", "b", "c")))
                .build(SYBASE))
                        .isEqualTo("SELECT * FROM persons AS `p` WHERE NOT (`dba`.`persons`.`lastname` = `dba`.`persons`.`nickname` AND `dba`.`persons`.`forename` IS NULL AND `dba`.`persons`.`nickname` NOT IN ('a', 'b', 'c'))");
    }

    @Test
    void testGetDialectByName()
    {
        assertThat(select().from(PERSONS).build("MYSQL")).isEqualTo("SELECT * FROM `dba`.`persons`");
        assertThat(select().from(PERSONS).build("MYSQL", enabled())).isEqualTo("SELECT\n  *\nFROM\n  `dba`.`persons`");
    }

    @Test
    void testBuildComplexSelect()
    {
        var subCondition = Condition.emptyCondition();

        subCondition = subCondition.and(LASTNAME.isEqualTo("Schumacher")
                .or(Condition.plain("IsNull(COL, '') != ''")));

        subCondition = subCondition.and(FORENAME.isNotNull());

        List<String> names = Arrays.asList("Schubi", null, "Ronny");

        var select = selectDistinct(FORENAME, LASTNAME, SIZE.as("Gr\\ö`ße"))
                .select(Selectable.plain("IsNull(`COL`, '')").as("Color"), sum(AGE).as("ageSum"))
                .from(select(count(FORENAME).as("foreCount")).from(PERSONS).as("sub"))
                .leftOuterJoin(PERSONS.as("q")
                        .on(FORENAME.isEqualTo(NICKNAME)
                                .and(AGE.isGreaterThan(SIZE))))
                .leftJoin(PERSONS.on(AGE.isEqualTo(FORENAME)))
                .where(FORENAME.isNotEqualTo(LASTNAME)
                        .andNot(LASTNAME.isEqualTo("Sch'umach\\er"))
                        .and(NICKNAME.isIn(names))
                        .andNot(Condition.plain("IsNull(`COL`, '') != ''"))
                        .and(FORENAME.isNotNull())
                        .or(LASTNAME.isEqualTo("Künzel"))
                        .and(AGE.isEqualTo(12))
                        .orNot(SIZE.isBetween(40.1234, 50.9876))
                        .or(AGE.isLessThan((Number) null))
                        .and(LASTNAME.isNotLike("Nils", AFTER))
                        .and(HAPPENING.isEqualTo(LocalDateTime.of(2019, 2, 22, 21, 11)))
                        .and(min(AGE).isGreaterThanOrEqualTo(50))
                        .and(subCondition)
                        .and(nested(AGE.isEqualTo(FORENAME).and(FORENAME.isNotNull()))
                                .or(FORENAME.isNull().and(AGE.isEqualTo(12))))
                        .and(BIRTHDAY.isBeforeOrEqualTo(LocalDate.of(2019, 7, 31).minusYears(10))
                                .or(BIRTHDAY.isLike("2019-05-", AFTER))))
                .groupBy(LASTNAME, FORENAME)
                .groupBy("foreCount")
                .having(sum(AGE).isGreaterThan(20)
                        .and(SIZE.isBetween(120, 150)))
                .orderBy(LASTNAME)
                .orderDescendingBy(FORENAME)
                .limit(100, 10);

        select.println(MYSQL, enabled());
        select.println(SYBASE, enabled());

        assertThat(select.build(MYSQL))
                .isEqualTo("SELECT DISTINCT `dba`.`persons`.`forename`, `dba`.`persons`.`lastname`, `dba`.`persons`.`size` AS `Gr\\\\ö\\`ße`, IsNull(`COL`, '') AS `Color`, SUM(`dba`.`persons`.`age`) AS `ageSum` FROM (SELECT COUNT(`dba`.`persons`.`forename`) AS `foreCount` FROM `dba`.`persons`) AS `sub` LEFT OUTER JOIN `dba`.`persons` AS `q` ON (`dba`.`persons`.`forename` = `dba`.`persons`.`nickname` AND `dba`.`persons`.`age` > `dba`.`persons`.`size`) LEFT JOIN `dba`.`persons` ON `dba`.`persons`.`age` = `dba`.`persons`.`forename` WHERE (`dba`.`persons`.`forename` != `dba`.`persons`.`lastname` AND NOT `dba`.`persons`.`lastname` = 'Sch\\'umach\\\\er' AND `dba`.`persons`.`nickname` IN ('Schubi', NULL, 'Ronny') AND NOT IsNull(`COL`, '') != '' AND `dba`.`persons`.`forename` IS NOT NULL OR `dba`.`persons`.`lastname` = 'Künzel' AND `dba`.`persons`.`age` = 12 OR NOT `dba`.`persons`.`size` BETWEEN 40.1234 AND 50.9876 OR `dba`.`persons`.`age` < NULL AND `dba`.`persons`.`lastname` NOT LIKE 'Nils%' AND `dba`.`persons`.`happening` = '2019-02-22 21:11:00.000000' AND MIN(`dba`.`persons`.`age`) >= 50 AND (`dba`.`persons`.`lastname` = 'Schumacher' OR IsNull(COL, '') != '' AND `dba`.`persons`.`forename` IS NOT NULL) AND ((`dba`.`persons`.`age` = `dba`.`persons`.`forename` AND `dba`.`persons`.`forename` IS NOT NULL) OR (`dba`.`persons`.`forename` IS NULL AND `dba`.`persons`.`age` = 12)) AND (`dba`.`persons`.`birthday` <= '2009-07-31' OR `dba`.`persons`.`birthday` LIKE '2019-05-%')) GROUP BY `dba`.`persons`.`lastname`, `dba`.`persons`.`forename`, foreCount HAVING (SUM(`dba`.`persons`.`age`) > 20 AND `dba`.`persons`.`size` BETWEEN 120 AND 150) ORDER BY `dba`.`persons`.`lastname` ASC, `dba`.`persons`.`forename` DESC LIMIT 10, 100");

        assertThat(select.build(SYBASE))
                .isEqualTo("SELECT TOP 100 START AT 11 DISTINCT `dba`.`persons`.`forename`, `dba`.`persons`.`lastname`, `dba`.`persons`.`size` AS `Gr\\ö``ße`, IsNull(`COL`, '') AS `Color`, SUM(`dba`.`persons`.`age`) AS `ageSum` FROM (SELECT COUNT(`dba`.`persons`.`forename`) AS `foreCount` FROM `dba`.`persons`) AS `sub` LEFT OUTER JOIN `dba`.`persons` AS `q` ON (`dba`.`persons`.`forename` = `dba`.`persons`.`nickname` AND `dba`.`persons`.`age` > `dba`.`persons`.`size`) LEFT JOIN `dba`.`persons` ON `dba`.`persons`.`age` = `dba`.`persons`.`forename` WHERE (`dba`.`persons`.`forename` != `dba`.`persons`.`lastname` AND NOT `dba`.`persons`.`lastname` = 'Sch''umach\\er' AND `dba`.`persons`.`nickname` IN ('Schubi', NULL, 'Ronny') AND NOT IsNull(`COL`, '') != '' AND `dba`.`persons`.`forename` IS NOT NULL OR `dba`.`persons`.`lastname` = 'Künzel' AND `dba`.`persons`.`age` = 12 OR NOT `dba`.`persons`.`size` BETWEEN 40.1234 AND 50.9876 OR `dba`.`persons`.`age` < NULL AND `dba`.`persons`.`lastname` NOT LIKE 'Nils%' AND `dba`.`persons`.`happening` = '2019-02-22 21:11:00.000000' AND MIN(`dba`.`persons`.`age`) >= 50 AND (`dba`.`persons`.`lastname` = 'Schumacher' OR IsNull(COL, '') != '' AND `dba`.`persons`.`forename` IS NOT NULL) AND ((`dba`.`persons`.`age` = `dba`.`persons`.`forename` AND `dba`.`persons`.`forename` IS NOT NULL) OR (`dba`.`persons`.`forename` IS NULL AND `dba`.`persons`.`age` = 12)) AND (`dba`.`persons`.`birthday` <= '2009-07-31' OR `dba`.`persons`.`birthday` LIKE '2019-05-%')) GROUP BY `dba`.`persons`.`lastname`, `dba`.`persons`.`forename`, foreCount HAVING (SUM(`dba`.`persons`.`age`) > 20 AND `dba`.`persons`.`size` BETWEEN 120 AND 150) ORDER BY `dba`.`persons`.`lastname` ASC, `dba`.`persons`.`forename` DESC");

        assertThat(select.build(MYSQL, enabled()))
                .isEqualTo("SELECT DISTINCT\n" //
                        + "  `dba`.`persons`.`forename`,\n" //
                        + "  `dba`.`persons`.`lastname`,\n" //
                        + "  `dba`.`persons`.`size` AS `Gr\\\\ö\\`ße`,\n" //
                        + "  IsNull(`COL`, '') AS `Color`,\n" //
                        + "  SUM(`dba`.`persons`.`age`) AS `ageSum`\n" //
                        + "FROM\n" //
                        + "(\n" //
                        + "  SELECT\n" //
                        + "    COUNT(`dba`.`persons`.`forename`) AS `foreCount`\n" //
                        + "  FROM\n" //
                        + "    `dba`.`persons`\n" //
                        + ") AS `sub`\n" //
                        + "LEFT OUTER JOIN `dba`.`persons` AS `q` ON\n" //
                        + "(\n" //
                        + "  `dba`.`persons`.`forename` = `dba`.`persons`.`nickname`\n" //
                        + "  AND `dba`.`persons`.`age` > `dba`.`persons`.`size`\n" //
                        + ")\n" //
                        + "LEFT JOIN `dba`.`persons` ON `dba`.`persons`.`age` = `dba`.`persons`.`forename`\n" //
                        + "WHERE\n" //
                        + "(\n" //
                        + "  `dba`.`persons`.`forename` != `dba`.`persons`.`lastname`\n" //
                        + "  AND NOT `dba`.`persons`.`lastname` = 'Sch\\'umach\\\\er'\n" //
                        + "  AND `dba`.`persons`.`nickname` IN ('Schubi', NULL, 'Ronny')\n" //
                        + "  AND NOT IsNull(`COL`, '') != ''\n" //
                        + "  AND `dba`.`persons`.`forename` IS NOT NULL\n" //
                        + "  OR `dba`.`persons`.`lastname` = 'Künzel'\n" //
                        + "  AND `dba`.`persons`.`age` = 12\n" //
                        + "  OR NOT `dba`.`persons`.`size` BETWEEN 40.1234 AND 50.9876\n" //
                        + "  OR `dba`.`persons`.`age` < NULL\n" //
                        + "  AND `dba`.`persons`.`lastname` NOT LIKE 'Nils%'\n" //
                        + "  AND `dba`.`persons`.`happening` = '2019-02-22 21:11:00.000000'\n" //
                        + "  AND MIN(`dba`.`persons`.`age`) >= 50\n" //
                        + "  AND\n" //
                        + "  (\n" //
                        + "    `dba`.`persons`.`lastname` = 'Schumacher'\n" //
                        + "    OR IsNull(COL, '') != ''\n" //
                        + "    AND `dba`.`persons`.`forename` IS NOT NULL\n" //
                        + "  )\n" //
                        + "  AND\n" //
                        + "  (\n" //
                        + "        (\n" //
                        + "      `dba`.`persons`.`age` = `dba`.`persons`.`forename`\n" //
                        + "      AND `dba`.`persons`.`forename` IS NOT NULL\n" //
                        + "    )\n" //
                        + "    OR\n" //
                        + "    (\n" //
                        + "      `dba`.`persons`.`forename` IS NULL\n" //
                        + "      AND `dba`.`persons`.`age` = 12\n" //
                        + "    )\n" //
                        + "  )\n" //
                        + "  AND\n" //
                        + "  (\n" //
                        + "    `dba`.`persons`.`birthday` <= '2009-07-31'\n" //
                        + "    OR `dba`.`persons`.`birthday` LIKE '2019-05-%'\n" //
                        + "  )\n" //
                        + ")\n" //
                        + "GROUP BY\n" //
                        + "  `dba`.`persons`.`lastname`,\n" //
                        + "  `dba`.`persons`.`forename`,\n" //
                        + "  foreCount\n" //
                        + "HAVING\n" //
                        + "(\n" //
                        + "  SUM(`dba`.`persons`.`age`) > 20\n" //
                        + "  AND `dba`.`persons`.`size` BETWEEN 120 AND 150\n" //
                        + ")\n" //
                        + "ORDER BY\n" //
                        + "  `dba`.`persons`.`lastname` ASC,\n" //
                        + "  `dba`.`persons`.`forename` DESC\n" //
                        + "LIMIT 10, 100");

        assertThat(select.build(SYBASE))
                .isEqualTo("SELECT TOP 100 START AT 11 DISTINCT `dba`.`persons`.`forename`, `dba`.`persons`.`lastname`, `dba`.`persons`.`size` AS `Gr\\ö``ße`, IsNull(`COL`, '') AS `Color`, SUM(`dba`.`persons`.`age`) AS `ageSum` FROM (SELECT COUNT(`dba`.`persons`.`forename`) AS `foreCount` FROM `dba`.`persons`) AS `sub` LEFT OUTER JOIN `dba`.`persons` AS `q` ON (`dba`.`persons`.`forename` = `dba`.`persons`.`nickname` AND `dba`.`persons`.`age` > `dba`.`persons`.`size`) LEFT JOIN `dba`.`persons` ON `dba`.`persons`.`age` = `dba`.`persons`.`forename` WHERE (`dba`.`persons`.`forename` != `dba`.`persons`.`lastname` AND NOT `dba`.`persons`.`lastname` = 'Sch''umach\\er' AND `dba`.`persons`.`nickname` IN ('Schubi', NULL, 'Ronny') AND NOT IsNull(`COL`, '') != '' AND `dba`.`persons`.`forename` IS NOT NULL OR `dba`.`persons`.`lastname` = 'Künzel' AND `dba`.`persons`.`age` = 12 OR NOT `dba`.`persons`.`size` BETWEEN 40.1234 AND 50.9876 OR `dba`.`persons`.`age` < NULL AND `dba`.`persons`.`lastname` NOT LIKE 'Nils%' AND `dba`.`persons`.`happening` = '2019-02-22 21:11:00.000000' AND MIN(`dba`.`persons`.`age`) >= 50 AND (`dba`.`persons`.`lastname` = 'Schumacher' OR IsNull(COL, '') != '' AND `dba`.`persons`.`forename` IS NOT NULL) AND ((`dba`.`persons`.`age` = `dba`.`persons`.`forename` AND `dba`.`persons`.`forename` IS NOT NULL) OR (`dba`.`persons`.`forename` IS NULL AND `dba`.`persons`.`age` = 12)) AND (`dba`.`persons`.`birthday` <= '2009-07-31' OR `dba`.`persons`.`birthday` LIKE '2019-05-%')) GROUP BY `dba`.`persons`.`lastname`, `dba`.`persons`.`forename`, foreCount HAVING (SUM(`dba`.`persons`.`age`) > 20 AND `dba`.`persons`.`size` BETWEEN 120 AND 150) ORDER BY `dba`.`persons`.`lastname` ASC, `dba`.`persons`.`forename` DESC");

        assertThat(select.build(SYBASE, enabled()))
                .isEqualTo("SELECT TOP 100 START AT 11 DISTINCT\n" //
                        + "  `dba`.`persons`.`forename`,\n" //
                        + "  `dba`.`persons`.`lastname`,\n" //
                        + "  `dba`.`persons`.`size` AS `Gr\\ö``ße`,\n" //
                        + "  IsNull(`COL`, '') AS `Color`,\n" //
                        + "  SUM(`dba`.`persons`.`age`) AS `ageSum`\n" //
                        + "FROM\n" //
                        + "(\n" //
                        + "  SELECT\n" //
                        + "    COUNT(`dba`.`persons`.`forename`) AS `foreCount`\n" //
                        + "  FROM\n" //
                        + "    `dba`.`persons`\n" //
                        + ") AS `sub`\n" //
                        + "LEFT OUTER JOIN `dba`.`persons` AS `q` ON\n" //
                        + "(\n" //
                        + "  `dba`.`persons`.`forename` = `dba`.`persons`.`nickname`\n" //
                        + "  AND `dba`.`persons`.`age` > `dba`.`persons`.`size`\n" //
                        + ")\n" //
                        + "LEFT JOIN `dba`.`persons` ON `dba`.`persons`.`age` = `dba`.`persons`.`forename`\n" //
                        + "WHERE\n" //
                        + "(\n" //
                        + "  `dba`.`persons`.`forename` != `dba`.`persons`.`lastname`\n" //
                        + "  AND NOT `dba`.`persons`.`lastname` = 'Sch''umach\\er'\n" //
                        + "  AND `dba`.`persons`.`nickname` IN ('Schubi', NULL, 'Ronny')\n" //
                        + "  AND NOT IsNull(`COL`, '') != ''\n" //
                        + "  AND `dba`.`persons`.`forename` IS NOT NULL\n" //
                        + "  OR `dba`.`persons`.`lastname` = 'Künzel'\n" //
                        + "  AND `dba`.`persons`.`age` = 12\n" //
                        + "  OR NOT `dba`.`persons`.`size` BETWEEN 40.1234 AND 50.9876\n" //
                        + "  OR `dba`.`persons`.`age` < NULL\n" //
                        + "  AND `dba`.`persons`.`lastname` NOT LIKE 'Nils%'\n" //
                        + "  AND `dba`.`persons`.`happening` = '2019-02-22 21:11:00.000000'\n" //
                        + "  AND MIN(`dba`.`persons`.`age`) >= 50\n" //
                        + "  AND\n" //
                        + "  (\n" //
                        + "    `dba`.`persons`.`lastname` = 'Schumacher'\n" //
                        + "    OR IsNull(COL, '') != ''\n" //
                        + "    AND `dba`.`persons`.`forename` IS NOT NULL\n" //
                        + "  )\n" //
                        + "  AND\n" //
                        + "  (\n" //
                        + "        (\n" //
                        + "      `dba`.`persons`.`age` = `dba`.`persons`.`forename`\n" //
                        + "      AND `dba`.`persons`.`forename` IS NOT NULL\n" //
                        + "    )\n" //
                        + "    OR\n" //
                        + "    (\n" //
                        + "      `dba`.`persons`.`forename` IS NULL\n" //
                        + "      AND `dba`.`persons`.`age` = 12\n" //
                        + "    )\n" //
                        + "  )\n" //
                        + "  AND\n" //
                        + "  (\n" //
                        + "    `dba`.`persons`.`birthday` <= '2009-07-31'\n" //
                        + "    OR `dba`.`persons`.`birthday` LIKE '2019-05-%'\n" //
                        + "  )\n" //
                        + ")\n" //
                        + "GROUP BY\n" //
                        + "  `dba`.`persons`.`lastname`,\n" //
                        + "  `dba`.`persons`.`forename`,\n" //
                        + "  foreCount\n" //
                        + "HAVING\n" //
                        + "(\n" //
                        + "  SUM(`dba`.`persons`.`age`) > 20\n" //
                        + "  AND `dba`.`persons`.`size` BETWEEN 120 AND 150\n" //
                        + ")\n" //
                        + "ORDER BY\n" //
                        + "  `dba`.`persons`.`lastname` ASC,\n" //
                        + "  `dba`.`persons`.`forename` DESC");

        assertThat(Select.copy(select).build(MYSQL)).isEqualTo(select.build(MYSQL));
    }

    @Test
    void testWithEmptyCondition()
    {
        assertThat(select().from(PERSONS).where(emptyCondition()).build()).isEqualTo("SELECT * FROM `dba`.`persons`");
    }

    @Test
    void testSimplestCopy()
    {
        assertThat(Select.copy(select().from(PERSONS)).build()).isEqualTo(select().from(PERSONS).build());
    }

    @Test
    void testClearSelects()
    {
        var select = select(LASTNAME).from(PERSONS);

        assertThat(select.getSelectables()).isNotNull();
        clearSelects(select);
        assertThat(select.getSelectables()).isNull();
    }

    @Test
    void testClearJoins()
    {
        var select = select(LASTNAME).from(PERSONS).leftJoin(GROUPS.on(NICKNAME.eq(FORENAME)));

        assertThat(select.getJoins()).isNotNull();
        clearJoins(select);
        assertThat(select.getJoins()).isNull();
    }

    @Test
    void testClearWheres()
    {
        var select = select(LASTNAME).from(PERSONS).where(FORENAME.isNull());

        assertThat(select.getWhere()).isNotNull();
        clearWheres(select);
        assertThat(select.getWhere()).isNull();
    }

    @Test
    void testClearGroupBys()
    {
        var select = select(LASTNAME).from(PERSONS).groupBy(FORENAME);

        assertThat(select.getGroupBys()).isNotNull();
        clearGroupBys(select);
        assertThat(select.getGroupBys()).isNull();
    }

    @Test
    void testClearHavings()
    {
        var select = select(LASTNAME).from(PERSONS).havingNot(count().gt(10));

        assertThat(select.getHaving()).isNotNull();
        clearHavings(select);
        assertThat(select.getHaving()).isNull();
    }

    @Test
    void testClearOrdering()
    {
        var select = select(LASTNAME).from(PERSONS).orderBy(LASTNAME);

        assertThat(select.getOrderBys()).isNotNull();
        clearOrdering(select);
        assertThat(select.getOrderBys()).isNull();
    }

    @Test
    void testClearLimit()
    {
        var select = select(LASTNAME).from(PERSONS).limit(5);

        assertThat(select.getLimitation()).isNotNull();
        clearLimit(select);
        assertThat(select.getLimitation()).isNull();
    }

    @Test
    void testJoins()
    {
        assertThat(select().from(PERSONS).join(GROUPS.on(NAME.eq(LASTNAME))).build())
                .isEqualTo("SELECT * FROM `dba`.`persons` JOIN `dba`.`groups` ON `dba`.`groups`.`name` = `dba`.`persons`.`lastname`");

        assertThat(select().from(PERSONS).leftJoin(GROUPS.on(NAME.eq(LASTNAME))).build())
                .isEqualTo("SELECT * FROM `dba`.`persons` LEFT JOIN `dba`.`groups` ON `dba`.`groups`.`name` = `dba`.`persons`.`lastname`");

        assertThat(select().from(PERSONS).rightJoin(GROUPS.on(NAME.eq(LASTNAME))).build())
                .isEqualTo("SELECT * FROM `dba`.`persons` RIGHT JOIN `dba`.`groups` ON `dba`.`groups`.`name` = `dba`.`persons`.`lastname`");

        assertThat(select().from(PERSONS).innerJoin(GROUPS.on(NAME.eq(LASTNAME))).build())
                .isEqualTo("SELECT * FROM `dba`.`persons` INNER JOIN `dba`.`groups` ON `dba`.`groups`.`name` = `dba`.`persons`.`lastname`");

        assertThat(select().from(PERSONS).leftOuterJoin(GROUPS.on(NAME.eq(LASTNAME))).build())
                .isEqualTo("SELECT * FROM `dba`.`persons` LEFT OUTER JOIN `dba`.`groups` ON `dba`.`groups`.`name` = `dba`.`persons`.`lastname`");

        assertThat(select().from(PERSONS).rightOuterJoin(GROUPS.on(NAME.eq(LASTNAME))).build())
                .isEqualTo("SELECT * FROM `dba`.`persons` RIGHT OUTER JOIN `dba`.`groups` ON `dba`.`groups`.`name` = `dba`.`persons`.`lastname`");

        assertThat(select().from(PERSONS).fullOuterJoin(GROUPS.on(NAME.eq(LASTNAME))).build())
                .isEqualTo("SELECT * FROM `dba`.`persons` FULL OUTER JOIN `dba`.`groups` ON `dba`.`groups`.`name` = `dba`.`persons`.`lastname`");
    }
}
