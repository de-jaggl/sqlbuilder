package de.jaggl.sqlbuilder.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DoubleSizeTest
{
    @Test
    void testValueOf()
    {
        assertThat(DoubleSize.valueOf(null)).isNull();
    }
}
