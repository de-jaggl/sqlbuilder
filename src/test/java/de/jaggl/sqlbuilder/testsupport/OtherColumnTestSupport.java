package de.jaggl.sqlbuilder.testsupport;

import de.jaggl.sqlbuilder.columns.Column;
import de.jaggl.sqlbuilder.columns.ColumnBuilder;
import de.jaggl.sqlbuilder.schema.Table;

public interface OtherColumnTestSupport<C extends Column, B extends ColumnBuilder<C>>
{
    B getColumnBuilder(Table table, String name);

    default C getOtherColumn()
    {
        return getColumnBuilder(Table.create("table"), "other").build();
    }

    default C getOtherColumn2()
    {
        return getColumnBuilder(Table.create("table"), "other2").build();
    }
}
