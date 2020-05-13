package de.jaggl.sqlbuilder.core.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ValuableTest
{
    @Test
    void testPlain()
    {
        assertThat(((Plain) Valuable.plain("anyValue").getValue()).getValue()).isEqualTo("anyValue");
    }
}
