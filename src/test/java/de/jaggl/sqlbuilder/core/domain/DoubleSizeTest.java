package de.jaggl.sqlbuilder.core.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.jaggl.sqlbuilder.core.domain.DoubleSize;

class DoubleSizeTest
{
    @Test
    void testValueOf()
    {
        assertThat(DoubleSize.valueOf(null)).isNull();
    }
}
