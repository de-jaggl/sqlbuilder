package de.jaggl.sqlbuilder.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class IntSizeTest
{
    @Test
    public void testValueOf()
    {
        assertThat(IntSize.valueOf(null)).isNull();
    }
}
