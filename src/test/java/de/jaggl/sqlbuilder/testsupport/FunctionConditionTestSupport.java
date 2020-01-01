package de.jaggl.sqlbuilder.testsupport;

import static de.jaggl.sqlbuilder.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.queries.Queries.select;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Supplier;

import org.assertj.core.api.AbstractStringAssert;

import de.jaggl.sqlbuilder.columns.number.doubletype.DoubleColumn;
import de.jaggl.sqlbuilder.columns.number.doubletype.DoubleColumnBuilder;
import de.jaggl.sqlbuilder.conditions.Condition;
import de.jaggl.sqlbuilder.schema.Table;

public abstract class FunctionConditionTestSupport implements OtherColumnTestSupport<DoubleColumn, DoubleColumnBuilder>
{
    @Override
    public DoubleColumnBuilder getColumnBuilder(Table table, String name)
    {
        return new DoubleColumnBuilder(table, name);
    }

    private static final Table TABLE = Table.create("table");

    protected AbstractStringAssert<?> assertCondition(Supplier<Condition> functionFunction)
    {
        return assertThat(select()
                .from(TABLE)
                .where(functionFunction.get())
                .build(MYSQL)
                .substring(28));
    }
}
