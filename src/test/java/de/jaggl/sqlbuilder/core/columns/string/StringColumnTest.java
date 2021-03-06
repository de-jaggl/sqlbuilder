package de.jaggl.sqlbuilder.core.columns.string;

import static de.jaggl.sqlbuilder.core.domain.LikeType.AFTER;
import static de.jaggl.sqlbuilder.core.domain.LikeType.BEFORE;
import static de.jaggl.sqlbuilder.core.domain.LikeType.BOTH;
import static de.jaggl.sqlbuilder.core.domain.Placeholder.placeholder;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.core.columns.ColumnTest;
import de.jaggl.sqlbuilder.core.schema.Table;

class StringColumnTest<C extends StringColumn<?>, B extends StringColumnBuilder<B, C>> extends ColumnTest<C, B, CharSequence>
{
    @SuppressWarnings("unchecked")
    @Override
    public B getColumnBuilder(Table table, String name)
    {
        return (B) new VarCharColumnBuilder(table, name);
    }

    @Test
    void testStringColumnConditions()
    {
        assertCondition(column -> column.isEqualTo("ABC")).isEqualTo("= 'ABC'");
        assertCondition(column -> column.eq("ABC")).isEqualTo("= 'ABC'");
        assertCondition(column -> column.isEqualTo((String) null)).isEqualTo("IS NULL");
        assertCondition(column -> column.eq((String) null)).isEqualTo("IS NULL");
        assertCondition(column -> column.isNotEqualTo("ABC")).isEqualTo("!= 'ABC'");
        assertCondition(column -> column.nEq("ABC")).isEqualTo("!= 'ABC'");
        assertCondition(column -> column.isNotEqualTo((String) null)).isEqualTo("IS NOT NULL");
        assertCondition(column -> column.nEq((String) null)).isEqualTo("IS NOT NULL");
        assertCondition(column -> column.isIn("a", "b", "c")).isEqualTo("IN ('a', 'b', 'c')");
        assertCondition(column -> column.isIn(List.of("a", "b", "c"))).isEqualTo("IN ('a', 'b', 'c')");
        assertCondition(column -> column.isIn(placeholder())).isEqualTo("IN (?)");
        assertCondition(column -> column.isNotIn("a", "b", "c")).isEqualTo("NOT IN ('a', 'b', 'c')");
        assertCondition(column -> column.isNotIn(List.of("a", "b", "c"))).isEqualTo("NOT IN ('a', 'b', 'c')");
        assertCondition(column -> column.isNotIn(placeholder())).isEqualTo("NOT IN (?)");
        assertCondition(column -> column.isLike("anyValue")).isEqualTo("LIKE 'anyValue'");
        assertCondition(column -> column.isLike((String) null)).isEqualTo("IS NULL");
        assertCondition(column -> column.isLike("anyValue", BEFORE)).isEqualTo("LIKE '%anyValue'");
        assertCondition(column -> column.isLike((String) null, BEFORE)).isEqualTo("IS NULL");
        assertCondition(column -> column.isLike("anyValue", AFTER)).isEqualTo("LIKE 'anyValue%'");
        assertCondition(column -> column.isLike((String) null, AFTER)).isEqualTo("IS NULL");
        assertCondition(column -> column.isLike("anyValue", BOTH)).isEqualTo("LIKE '%anyValue%'");
        assertCondition(column -> column.isLike((String) null, BOTH)).isEqualTo("IS NULL");
        assertCondition(column -> column.isLike(getOtherColumn())).isEqualTo("LIKE `table`.`other`");
        assertCondition(column -> column.isLike(placeholder())).isEqualTo("LIKE ?");
        assertCondition(column -> column.isNotLike("anyValue")).isEqualTo("NOT LIKE 'anyValue'");
        assertCondition(column -> column.isNotLike((String) null)).isEqualTo("IS NOT NULL");
        assertCondition(column -> column.isNotLike("anyValue", BEFORE)).isEqualTo("NOT LIKE '%anyValue'");
        assertCondition(column -> column.isNotLike((String) null, BEFORE)).isEqualTo("IS NOT NULL");
        assertCondition(column -> column.isNotLike("anyValue", AFTER)).isEqualTo("NOT LIKE 'anyValue%'");
        assertCondition(column -> column.isNotLike((String) null, AFTER)).isEqualTo("IS NOT NULL");
        assertCondition(column -> column.isNotLike("anyValue", BOTH)).isEqualTo("NOT LIKE '%anyValue%'");
        assertCondition(column -> column.isNotLike((String) null, BOTH)).isEqualTo("IS NOT NULL");
        assertCondition(column -> column.isNotLike(getOtherColumn())).isEqualTo("NOT LIKE `table`.`other`");
        assertCondition(column -> column.isNotLike(placeholder())).isEqualTo("NOT LIKE ?");
    }
}
