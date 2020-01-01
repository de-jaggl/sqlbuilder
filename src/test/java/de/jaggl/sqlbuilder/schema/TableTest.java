package de.jaggl.sqlbuilder.schema;

import static de.jaggl.sqlbuilder.dialect.Dialects.MYSQL;
import static de.jaggl.sqlbuilder.utils.Indentation.disabled;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.columns.datetime.DateColumnBuilder;
import de.jaggl.sqlbuilder.columns.datetime.DateTimeColumnBuilder;
import de.jaggl.sqlbuilder.columns.number.doubletype.DecimalColumnBuilder;
import de.jaggl.sqlbuilder.columns.number.doubletype.DoubleColumnBuilder;
import de.jaggl.sqlbuilder.columns.number.doubletype.FloatColumnBuilder;
import de.jaggl.sqlbuilder.columns.number.integer.BigIntColumnBuilder;
import de.jaggl.sqlbuilder.columns.number.integer.IntColumnBuilder;
import de.jaggl.sqlbuilder.columns.number.integer.MediumIntColumnBuilder;
import de.jaggl.sqlbuilder.columns.number.integer.SmallIntColumnBuilder;
import de.jaggl.sqlbuilder.columns.number.integer.TinyIntColumnBuilder;
import de.jaggl.sqlbuilder.columns.string.CharColumnBuilder;
import de.jaggl.sqlbuilder.columns.string.TextColumnBuilder;
import de.jaggl.sqlbuilder.columns.string.VarCharColumnBuilder;
import de.jaggl.sqlbuilder.domain.BuildingContext;

class TableTest
{
    @Test
    void testGetFullNameOrAlias()
    {
        assertThat(Table.create("table").getFullNameOrAlias(new BuildingContext(MYSQL, disabled().getDelimiter()))).isEqualTo("`table`");
        assertThat(Table.create("table").as("alias").getFullNameOrAlias(new BuildingContext(MYSQL, disabled().getDelimiter()))).isEqualTo("`alias`");
    }

    @Test
    void testColumnMethods()
    {
        var table = Table.create("table");
        assertThat(table.charColumn("anyName")).isInstanceOf(CharColumnBuilder.class);
        assertThat(table.varCharColumn("anyName")).isInstanceOf(VarCharColumnBuilder.class);
        assertThat(table.textColumn("anyName")).isInstanceOf(TextColumnBuilder.class);
        assertThat(table.tinyIntColumn("anyName")).isInstanceOf(TinyIntColumnBuilder.class);
        assertThat(table.smallIntColumn("anyName")).isInstanceOf(SmallIntColumnBuilder.class);
        assertThat(table.mediumIntColumn("anyName")).isInstanceOf(MediumIntColumnBuilder.class);
        assertThat(table.intColumn("anyName")).isInstanceOf(IntColumnBuilder.class);
        assertThat(table.bigIntColumn("anyName")).isInstanceOf(BigIntColumnBuilder.class);
        assertThat(table.dateColumn("anyName")).isInstanceOf(DateColumnBuilder.class);
        assertThat(table.dateTimeColumn("anyName")).isInstanceOf(DateTimeColumnBuilder.class);
        assertThat(table.doubleColumn("anyName")).isInstanceOf(DoubleColumnBuilder.class);
        assertThat(table.floatColumn("anyName")).isInstanceOf(FloatColumnBuilder.class);
        assertThat(table.decimalColumn("anyName")).isInstanceOf(DecimalColumnBuilder.class);
    }
}
