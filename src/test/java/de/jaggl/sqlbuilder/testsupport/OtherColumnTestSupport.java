package de.jaggl.sqlbuilder.testsupport;

import de.jaggl.sqlbuilder.columns.Column;
import de.jaggl.sqlbuilder.columns.number.doubletype.DoubleColumnBuilder;
import de.jaggl.sqlbuilder.schema.Table;

public interface OtherColumnTestSupport
{
    default Column getOtherColumn()
    {
        return new DoubleColumnBuilder(Table.create("table"), "other").build();
    }

    default Column getOtherColumn2()
    {
        return new DoubleColumnBuilder(Table.create("table"), "other2").build();
    }
}
