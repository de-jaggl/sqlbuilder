package de.jaggl.sqlbuilder.core.testsupport;

import de.jaggl.sqlbuilder.core.columns.Column;
import de.jaggl.sqlbuilder.core.columns.number.doubletype.DoubleColumnBuilder;
import de.jaggl.sqlbuilder.core.columns.number.integer.IntColumnBuilder;
import de.jaggl.sqlbuilder.core.schema.Table;

public interface OtherColumnTestSupport
{
    default Column getOtherColumn()
    {
        return new DoubleColumnBuilder(Table.create("table"), "other").build();
    }

    default Column getOtherColumn2()
    {
        return new IntColumnBuilder(Table.create("table"), "other2").build();
    }
}
