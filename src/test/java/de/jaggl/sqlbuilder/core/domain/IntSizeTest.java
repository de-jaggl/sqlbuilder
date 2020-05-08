package de.jaggl.sqlbuilder.core.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.core.domain.IntSize;

class IntSizeTest
{
    @Test
    void testValueOf()
    {
        assertThat(IntSize.valueOf(null)).isNull();
    }
}
