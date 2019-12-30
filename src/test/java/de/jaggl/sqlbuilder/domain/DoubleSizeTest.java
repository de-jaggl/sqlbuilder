package de.jaggl.sqlbuilder.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DoubleSizeTest
{
    @Test
    public void testValueOf()
    {
        assertThat(DoubleSize.valueOf(null)).isNull();
    }
}
