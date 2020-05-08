package de.jaggl.sqlbuilder.core.domain;

import static de.jaggl.sqlbuilder.core.domain.Placeholder.placeholder;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.core.columns.string.VarCharColumn;
import de.jaggl.sqlbuilder.core.domain.Plain;
import de.jaggl.sqlbuilder.core.schema.Table;

class PlaceholderTest
{
    private static final Table TABLE = Table.create("table");

    private static final VarCharColumn COLUMN = TABLE.varCharColumn("column").build();

    @Test
    void testPlaceholder()
    {
        assertThat(((Plain) placeholder().getValue()).getValue()).isEqualTo("?");
        assertThat(((Plain) placeholder(COLUMN).getValue()).getValue()).isEqualTo(":column");
        assertThat(((Plain) placeholder("value").getValue()).getValue()).isEqualTo(":value");
    }
}
