package de.jaggl.sqlbuilder.core.testsupport;

import static de.jaggl.sqlbuilder.core.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.core.queries.Queries.select;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Supplier;

import org.assertj.core.api.AbstractStringAssert;

import de.jaggl.sqlbuilder.core.conditions.Condition;
import de.jaggl.sqlbuilder.core.schema.Table;

public abstract class FunctionConditionTestSupport implements OtherColumnTestSupport
{
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
